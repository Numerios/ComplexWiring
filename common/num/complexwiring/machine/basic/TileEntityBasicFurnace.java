package num.complexwiring.machine.basic;

import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import num.complexwiring.api.base.TileEntityInventoryBase;
import num.complexwiring.core.InventoryHelper;

public class TileEntityBasicFurnace extends TileEntityInventoryBase implements ISidedInventory {

    private static final int[] SLOTS_OUTPUT = new int[]{2, 3};
    private static final int[] SLOTS_TOP = new int[]{0};
    private static final int[] SLOTS_BOTTOM = new int[]{2, 3, 1};

    private ItemStack recipe;
    private int recipeNeededTime = 160;
    public int processTime = 0;
    public int burnTime = 0;
    private int fuelBurnTime = 0;

    public TileEntityBasicFurnace() {
        super(4, EnumBasicMachine.FURNACE.getFullUnlocalizedName());
    }

    public void update() {
        super.update();

        if (!worldObj.isRemote) {
            if (recipe == null) {
                if (inventory[0] != null && FurnaceRecipes.smelting().getSmeltingResult(inventory[0]) != null) {
                    recipe = FurnaceRecipes.smelting().getSmeltingResult(inventory[0]).copy();

                    takeFuel();
                    if (burnTime > 0) {
                        inventory[0].stackSize--;

                        if (getStackInSlot(0).stackSize <= 0) {
                            setInventorySlotContents(0, null);
                        }
                    } else {
                        recipe = null;
                    }
                }
            }
            if (burnTime > 0) {
                burnTime--;
            }
            if (recipe != null) {
                processTime++;

                if (processTime >= recipeNeededTime) {
                    endProcessing();
                }
                if (burnTime == 0) takeFuel();
            }
            if (ticks % 4 == 0) {
                worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
                markDirty();
            }
        }
    }

    public void endProcessing() {
        if (recipe != null) {
            for (int i : SLOTS_OUTPUT) {
                if (getStackInSlot(i) == null) {
                    setInventorySlotContents(i, recipe);
                    break;
                } else {
                    int adding = InventoryHelper.canMerge(getStackInSlot(i), recipe);
                    if (adding > 0) {
                        getStackInSlot(i).stackSize = getStackInSlot(i).stackSize + adding;
                        recipe.splitStack(adding);
                        if (recipe.stackSize == 0) break;
                    }

                }
            }
        }
        recipe = null;
        processTime = 0;
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
                fuelBurnTime = 0;
                processTime = 0;
            }
        }
    }

    @Override
    public void writePacketNBT(NBTTagCompound nbt) {
        super.writePacketNBT(nbt);

        NBTTagCompound recipeTag = new NBTTagCompound();
        if (recipe != null) recipe.writeToNBT(recipeTag);
        nbt.setTag("recipe", recipeTag);
        nbt.setShort("burnTime", (short) burnTime);
        nbt.setShort("processTime", (short) processTime);
        nbt.setShort("fuelBurnTime", (short) fuelBurnTime);
    }

    @Override
    public void readPacketNBT(NBTTagCompound nbt) {
        super.readPacketNBT(nbt);

        recipe = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("recipe"));
        burnTime = nbt.getShort("burnTime");
        processTime = nbt.getShort("processTime");
        recipeNeededTime = 160;
        fuelBurnTime = nbt.getShort("fuelBurnTime");
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int slot) {
        return slot == 0 ? SLOTS_BOTTOM : (slot == 1 ? SLOTS_TOP : SLOTS_OUTPUT);
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack is, int side) {
        return !(slot == 2 || slot == 3) || isItemValidForSlot(slot, is);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack is, int side) {
        return (slot == 2 || slot == 3);
    }

    public int getProcessedTimeScaled(int scale) {
        if (processTime == 0 || recipeNeededTime == 0) {
            return 0;
        }
        return processTime * scale / recipeNeededTime;
    }

    public int getBurnTimeScaled(int scale) {
        if (burnTime == 0 || fuelBurnTime == 0) {
            return 0;
        }
        return burnTime * scale / fuelBurnTime;
    }

    public int getFuelBurnTime(ItemStack is) {
        if (is != null) {
            return TileEntityFurnace.getItemBurnTime(is);
        }
        return 0;
    }

}
