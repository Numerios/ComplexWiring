package num.complexwiring.machine.powered;

import net.minecraft.block.Block;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet;
import num.complexwiring.api.base.TileEntityPoweredBase;
import num.complexwiring.api.recipe.RecipeRandomOutput;
import num.complexwiring.api.vec.Vector3;
import num.complexwiring.core.InventoryHelper;
import num.complexwiring.core.PacketHandler;
import num.complexwiring.recipe.OrelyzerRecipe;
import num.complexwiring.recipe.RecipeManager;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class TileEntityPoweredOrelyzer extends TileEntityPoweredBase implements ISidedInventory {
    private static final int[] SLOTS_OUTPUT = new int[]{2, 3};
    private static final int[] SLOTS_TOP = new int[]{0};
    private static final int[] SLOTS_BOTTOM = new int[]{2, 3, 1};
    private static final int[] SLOTS_SIDE = new int[]{1};
    public int machineProcessTime = 0;
    Random rand = new Random();
    private int machineNeededProcessTime = 0;
    private OrelyzerRecipe currentRecipe;
    private ArrayList<ItemStack> currentRecipeOutput;

    public TileEntityPoweredOrelyzer() {
        super(4, EnumPoweredMachine.ORELYZER.getFullUnlocalizedName());
    }

    @Override
    public void setup() {
        super.setup();
        currentRecipeOutput = new ArrayList<ItemStack>();
        currentRecipe = new OrelyzerRecipe(new ItemStack(Block.wood, 1), 120,
                new RecipeRandomOutput(new ItemStack(Item.diamond, 1), 0.1F),
                new RecipeRandomOutput(new ItemStack(Item.stick, 2), 0.8F),
                new RecipeRandomOutput(new ItemStack(Item.stick, 1), 1F));
    }

    @Override
    public void update() {
        super.update();

        if (worldObj.isRemote) {
        }
        if (!worldObj.isRemote) {
            boolean processable = canProcess();
            if (isPowered() && processable) {
                if (machineProcessTime == 0) {
                    initProcessing();
                }
                powerHandler.useEnergy(0, MIN_ENERGY, true);
                machineProcessTime++;
                machineNeededProcessTime = currentRecipe.getNeededPower();
                if (machineProcessTime == machineNeededProcessTime) {
                    machineProcessTime = 0;
                    endProcessing();
                }
            } else {
                machineProcessTime = 0;
                /*currentRecipe = null;           //TODO NULL WHEN POWER GOES OUT
                currentRecipeOutput = null;*/
            }
            if (ticks % 4 == 0) {
                PacketHandler.sendPacket(getDescriptionPacket(), worldObj, Vector3.get(this));
            }
        }
        //TODO: DO NOT LOAD IT ALL THE TIME!
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        onInventoryChanged();
    }

    private boolean canProcess() {
        if (currentRecipe == null) {
            if (RecipeManager.get(getStackInSlot(0)) == null) {
                return false;
            } else {
                currentRecipe = RecipeManager.get(getStackInSlot(0));
            }

            currentRecipeOutput = currentRecipe.getCompleteOutput(rand);
            if (currentRecipeOutput == null || currentRecipeOutput.size() < 1) {
                return false;
            }
        }
        if (getStackInSlot(2) == null || getStackInSlot(3) == null) {
            return true;
        }
        ItemStack[] possibleOutput = new ItemStack[2];

        if (getStackInSlot(2) != null) {
            possibleOutput[0] = getStackInSlot(2).copy();
        }
        if (getStackInSlot(3) != null) {
            possibleOutput[1] = getStackInSlot(3).copy();
        }

        for (ItemStack is : currentRecipeOutput) {
            if (InventoryHelper.canMerge(possibleOutput[0], is) + InventoryHelper.canMerge(possibleOutput[1], is) < is.stackSize) {
                return false;
            }
        }
        return true;
    }

    public void initProcessing() {
        if (canProcess() && getStackInSlot(0) != null) {
            inventory[0].stackSize--;

            if (getStackInSlot(0).stackSize <= 0) {
                setInventorySlotContents(0, null);
            }
        }
    }

    public void endProcessing() {
        if (currentRecipeOutput != null && currentRecipeOutput.size() > 0) {
            for (ItemStack output : currentRecipeOutput) {
                if (output != null && output.stackSize != 0) {
                    for (int i : SLOTS_OUTPUT) {
                        if (getStackInSlot(i) == null) {
                            setInventorySlotContents(i, output);
                            break;
                        } else {
                            int adding = InventoryHelper.canMerge(getStackInSlot(i), output);
                            if (adding > 0) {
                                getStackInSlot(i).stackSize = getStackInSlot(i).stackSize + adding;
                                output.splitStack(adding);
                                if (output.stackSize == 0) break;
                            }

                        }
                    }
                }
            }
        }
        currentRecipe = null;
        currentRecipeOutput.clear();
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        nbt.setShort("processTime", (short) machineProcessTime);
        if (currentRecipe != null) {
            nbt.setInteger("currentRecipe", RecipeManager.toRecipeID(currentRecipe));
        }
        if (currentRecipeOutput != null) {
            NBTTagList outputNBT = new NBTTagList();
            for (ItemStack is : currentRecipeOutput) {
                if (is != null) {
                    NBTTagCompound itemNBT = new NBTTagCompound();
                    is.writeToNBT(itemNBT);
                    outputNBT.appendTag(itemNBT);
                }
            }
            nbt.setTag("currentOutput", outputNBT);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        machineProcessTime = nbt.getShort("processTime");
        currentRecipe = RecipeManager.fromRecipeID(nbt.getInteger("currentRecipe"));

        currentRecipeOutput = new ArrayList<ItemStack>();
        NBTTagList outputNBT = nbt.getTagList("currentOutput");
        for (int i = 0; i < outputNBT.tagCount(); i++) {
            NBTTagCompound itemNBT = (NBTTagCompound) outputNBT.tagAt(i);
            currentRecipeOutput.add(ItemStack.loadItemStackFromNBT(itemNBT));
        }
    }

    @Override
    public Packet getDescriptionPacket() {
        return PacketHandler.getPacket(this, EnumPoweredMachine.ORELYZER.ordinal());
    }

    @Override
    public void handlePacket(DataInputStream is) throws IOException {
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int slot) {
        return slot == 0 ? SLOTS_BOTTOM : (slot == 1 ? SLOTS_TOP : SLOTS_SIDE);
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack is, int side) {
        return isItemValidForSlot(slot, is);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack is, int side) {
        return slot == 2 || slot == 3;
    }

    public int getProcessedTimeScaled(int scale) {
        if (machineProcessTime == 0 || machineNeededProcessTime == 0) {
            return 0;
        }
        return machineProcessTime * scale / machineNeededProcessTime;
    }

    public int getEnergyScaled(int scale) {
        if (storedEnergy == 0 || !isPowered()) {
            return 0;
        }
        return (int) storedEnergy * scale / MAX_ENERGY;
    }

    public boolean isPowered() {
        if (currentRecipe.getNeededPower() == 0){
            return false;
        }
        return MIN_ENERGY * currentRecipe.getNeededPower() >= storedEnergy;
    }
}
