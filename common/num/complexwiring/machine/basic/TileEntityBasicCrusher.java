package num.complexwiring.machine.basic;

import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityFurnace;
import num.complexwiring.api.prefab.tile.TileEntityInventoryBase;
import num.complexwiring.api.recipe.CrusherRecipe;
import num.complexwiring.core.InventoryHelper;
import num.complexwiring.recipe.RecipeManager;

import java.util.ArrayList;
import java.util.Random;

public class TileEntityBasicCrusher extends TileEntityInventoryBase implements ISidedInventory {
    private static final int[] SLOTS_OUTPUT = new int[]{2, 3};
    private static final int[] SLOTS_TOP = new int[]{0};
    private static final int[] SLOTS_BOTTOM = new int[]{2, 3, 1};
    public int processTime = 0;
    public int burnTime = 0;
    private Random random = new Random();
    private CrusherRecipe recipe;
    private ArrayList<ItemStack> recipeOutput;
    private int recipeNeedTime = 0;
    private int fuelBurnTime = 0;

    public TileEntityBasicCrusher() {
        super(4, EnumBasicMachine.CRUSHER.getFullUnlocalizedName());
        recipeOutput = new ArrayList<ItemStack>();
    }

    public void update() {
        super.update();

        if (!worldObj.isRemote) {
            if (recipe == null) {
                if (canBeProcessed()) {
                    startProcessing();
                }
            }
            if (burnTime > 0) {
                burnTime--;
            }
            if (recipe != null) {
                if (burnTime > 0) {
                    processTime++;

                    if (processTime >= recipeNeedTime) {
                        endProcessing();
                    }
                }
                if (burnTime == 0) takeFuel();
            }
            if (ticks % 4 == 0) {
                worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
                markDirty();
            }
        }
    }

    public boolean canBeProcessed() {
        if (RecipeManager.get(RecipeManager.Type.CRUSHER, getStackInSlot(0)) == null) {
            return false;
        } else {
            recipe = (CrusherRecipe) RecipeManager.get(RecipeManager.Type.CRUSHER, getStackInSlot(0));
            takeFuel();
            return true;
        }
    }

    public void takeFuel() {
        if (burnTime == 0) {
            if (getStackInSlot(1) != null) {
                fuelBurnTime = getFuelBurnTime(inventory[1]);
                if (fuelBurnTime != 0) {
                    burnTime = fuelBurnTime;
                    inventory[1].stackSize--;
                    if (getStackInSlot(1).stackSize == 0) {
                        inventory[1] = inventory[1].getItem().getContainerItem(inventory[1]);
                    }
                }
            } else {
                recipe = null;
                recipeOutput.clear();
                recipeNeedTime = 0;
                fuelBurnTime = 0;
                processTime = 0;
            }
        }
    }

    public void startProcessing() {
        if (recipe != null && burnTime > 0) {
            recipeNeedTime = recipe.getNeededPower();
            recipeOutput = recipe.getOutput(random);

            inventory[0].stackSize--;

            if (getStackInSlot(0).stackSize <= 0) {
                setInventorySlotContents(0, null);
            }
        }
    }

    public void endProcessing() {
        if (recipeOutput != null && recipeOutput.size() > 0) {
            for (ItemStack output : recipeOutput) {
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
        recipe = null;
        recipeOutput.clear();
        recipeNeedTime = 0;
        processTime = 0;
    }

    public int getProcessedTimeScaled(int scale) {
        if (processTime == 0 || recipeNeedTime == 0) {
            return 0;
        }
        return processTime * scale / recipeNeedTime;
    }

    public int getBurnTimeScaled(int scale) {
        if (burnTime == 0 || fuelBurnTime == 0) {
            return 0;
        }
        return burnTime * scale / fuelBurnTime;
    }

    public int getFuelBurnTime(ItemStack is) {
        if (is != null) {
            return TileEntityFurnace.getItemBurnTime(is) / 4;
        }
        return 0;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int slot) {
        return slot == 0 ? SLOTS_BOTTOM : (slot == 1 ? SLOTS_TOP : SLOTS_OUTPUT);
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack is, int side) {
        return !(slot == 2 || slot == 3) && isItemValidForSlot(slot, is);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack is, int side) {
        return (slot == 2 || slot == 3);
    }

    @Override
    public void writePacketNBT(NBTTagCompound nbt) {
        super.writePacketNBT(nbt);

        nbt.setShort("recipe", (short) RecipeManager.toRecipeID(RecipeManager.Type.CRUSHER, recipe));
        nbt.setShort("burnTime", (short) burnTime);
        nbt.setShort("processTime", (short) processTime);
        nbt.setShort("recipeTime", (short) recipeNeedTime);
        nbt.setShort("fuelBurnTime", (short) fuelBurnTime);

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

    @Override
    public void readPacketNBT(NBTTagCompound nbt) {
        super.readPacketNBT(nbt);

        recipe = (CrusherRecipe) RecipeManager.fromRecipeID(RecipeManager.Type.CRUSHER, nbt.getShort("recipe"));
        burnTime = nbt.getShort("burnTime");
        processTime = nbt.getShort("processTime");
        recipeNeedTime = nbt.getShort("recipeTime");
        fuelBurnTime = nbt.getShort("fuelBurnTime");

        recipeOutput.clear();
        NBTTagList outputNBT = (NBTTagList) nbt.getTag("currentOutput");
        if (outputNBT != null) {
            for (int i = 0; i < outputNBT.tagCount(); i++) {
                NBTTagCompound itemNBT = outputNBT.getCompoundTagAt(i);
                recipeOutput.add(ItemStack.loadItemStackFromNBT(itemNBT));
            }
        }
    }
}
