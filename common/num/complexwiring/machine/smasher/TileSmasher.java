package num.complexwiring.machine.smasher;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.util.ForgeDirection;
import num.complexwiring.api.prefab.IFacing;
import num.complexwiring.api.prefab.tile.TileEntityBase;
import num.complexwiring.api.recipe.SmasherRecipe;
import num.complexwiring.api.vec.Vector3;
import num.complexwiring.base.ItemScrewdriver;
import num.complexwiring.core.Logger;
import num.complexwiring.recipe.RecipeManager;

import java.util.ArrayList;
import java.util.Random;

public class TileSmasher extends TileEntityBase implements IFacing {
    private ForgeDirection facing;
    private SmasherRecipe recipe;
    private ArrayList<ItemStack> recipeOutput;
    private int processTime = 0;
    private int recipeNeededPower = 0;

    public TileSmasher() {
        recipeOutput = new ArrayList<ItemStack>();
    }

    @Override
    public void update() {
        if (!world().isRemote) {
            Vector3 facingVec = pos().clone().step(facing);
            if (recipe == null) {
                if (RecipeManager.get(RecipeManager.Type.SMASHER, facingVec.getIS(world())) != null) {
                    recipe = (SmasherRecipe) RecipeManager.get(RecipeManager.Type.SMASHER, facingVec.getIS(world()));
                    if (recipe != null) { // + hasPower
                        recipeNeededPower = recipe.getNeededPower();
                        recipeOutput = recipe.getOutput(new Random());
                        processTime = 0;
                    } else {
                        recipe = null;
                    }
                }
            }
            if (recipe != null && facingVec.getIS(world()).isItemEqual(recipe.getInput())) {
                processTime++;
                if (processTime >= recipeNeededPower) {
                    endProcessing();
                }
            } else {
                resetProcessing();
            }
        }
        worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        markDirty();

    }

    private void resetProcessing() {
        recipe = null;
        recipeOutput.clear();
        recipeNeededPower = 0;
        processTime = 0;
    }


    public void endProcessing() {
        if (recipeOutput != null && recipeOutput.size() > 0) {
            for (ItemStack output : recipeOutput) {
                if (output != null && output.stackSize != 0) {
                    EntityItem entityItem = new EntityItem(world(), pos().getX() + 0.5, pos().getY() + 0.5, pos().getZ() + 0.5, output);
                    entityItem.setVelocity(0, 0, 0);
                    entityItem.delayBeforeCanPickup = 0;
                    world().spawnEntityInWorld(entityItem);
                }
            }
            pos().clone().step(facing).setBlock(world(), Blocks.air);
        }
        resetProcessing();
    }


    @Override
    public ForgeDirection getFacing() {
        return facing;
    }

    @Override
    public void setFacing(ForgeDirection dir) {
        Logger.debug("My new facing is: " + dir.toString());
        this.facing = dir;
    }

    public void onRightClick(EntityPlayer player) {
        ItemStack is = player.getCurrentEquippedItem();
        if (is != null) {
            if (is.getItem() instanceof ItemScrewdriver) {
                int playerFacing = MathHelper.floor_double(player.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
                this.setFacing(ForgeDirection.getOrientation(ForgeDirection.ROTATION_MATRIX[playerFacing][facing.ordinal()]));
            }
        }
    }

    public void writePacketNBT(NBTTagCompound nbt) {
        nbt.setShort("facing", (short) facing.ordinal());
        nbt.setShort("processTime", (short) processTime);
        nbt.setInteger("recipe", RecipeManager.toRecipeID(RecipeManager.Type.SMASHER, recipe));
        nbt.setShort("recipePower", (short) recipeNeededPower);

        if (recipeOutput != null) {
            NBTTagList outputNBT = new NBTTagList();
            for (ItemStack is : recipeOutput) {
                if (is != null) {
                    NBTTagCompound itemNBT = new NBTTagCompound();
                    is.writeToNBT(itemNBT);
                    outputNBT.appendTag(itemNBT);
                }
            }
            nbt.setTag("recipeOutput", outputNBT);
        }
    }

    public void readPacketNBT(NBTTagCompound nbt) {
        this.facing = ForgeDirection.getOrientation(nbt.getShort("facing"));

        processTime = nbt.getShort("processTime");
        recipe = (SmasherRecipe) RecipeManager.fromRecipeID(RecipeManager.Type.SMASHER, nbt.getInteger("recipe"));
        recipeNeededPower = nbt.getShort("recipePower");

        recipeOutput.clear();
        NBTTagList outputNBT = (NBTTagList) nbt.getTag("recipeOutput");
        if (outputNBT != null) {
            for (int i = 0; i < outputNBT.tagCount(); i++) {
                NBTTagCompound itemNBT = outputNBT.getCompoundTagAt(i);
                recipeOutput.add(ItemStack.loadItemStackFromNBT(itemNBT));
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        writePacketNBT(nbt);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        readPacketNBT(nbt);
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        writePacketNBT(nbt);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, this.blockMetadata, nbt);
    }

    public void onDataPacket(NetworkManager networkManager, S35PacketUpdateTileEntity packet) {
        readPacketNBT(packet.func_148857_g());
    }

    public float getProgress(int scale) {
        if (processTime == 0 || recipeNeededPower == 0) {
            return 0;
        }
        return processTime * scale / recipeNeededPower;
    }
}
