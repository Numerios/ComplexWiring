package num.complexwiring.machine.basic;

import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import num.complexwiring.api.base.TileEntityInventoryBase;
import num.complexwiring.api.vec.Vector3;
import num.complexwiring.core.InventoryHelper;
import num.complexwiring.core.PacketHandler;

public class TileEntityBasicFurnace extends TileEntityInventoryBase implements ISidedInventory {

    private static final int[] SLOTS_OUTPUT = new int[]{2, 3};
    private static final int[] SLOTS_TOP = new int[]{0};
    private static final int[] SLOTS_BOTTOM = new int[]{2, 3, 1};

    private ItemStack recipe;
    private int recipeNeedTime = 166;
    public int processTime = 0;
    public int burnTime = 0;
    private int fuelBurnTime = 0;

    public TileEntityBasicFurnace() {
        super(4, EnumBasicMachine.FURNACE.getFullUnlocalizedName());
    }

    public void update(){
        super.update();

        if (!worldObj.isRemote) {
            if(recipe == null) {
                if(inventory[0] != null && FurnaceRecipes.smelting().getSmeltingResult(inventory[0]) != null) {
                    recipe = FurnaceRecipes.smelting().getSmeltingResult(inventory[0]).copy();

                    takeFuel();
                    if(burnTime > 0){
                        inventory[0].stackSize--;

                        if (getStackInSlot(0).stackSize <= 0) {
                            setInventorySlotContents(0, null);
                        }
                    } else {
                        recipe = null;
                    }
                }
            }
            if(recipe != null) {
                if(burnTime > 0){
                    burnTime--;
                    processTime++;

                    if(processTime >= recipeNeedTime) {
                        endProcessing();
                    }
                }
                if(burnTime == 0) takeFuel();
            }
            if (ticks % 4 == 0) {
                PacketHandler.sendPacket(getDescriptionPacket(), worldObj, Vector3.get(this));
            }
        }
        //TODO: DO NOT LOAD IT ALL THE TIME!
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        onInventoryChanged();

    }

    public void endProcessing(){
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

    public void takeFuel(){
        if(burnTime == 0){
            if (getStackInSlot(1) != null) {
                fuelBurnTime = getFuelBurnTime(inventory[1]);
                if(fuelBurnTime != 0){
                    burnTime = fuelBurnTime;
                    inventory[1].stackSize--;
                    if (getStackInSlot(1).stackSize == 0) {
                        inventory[1] = inventory[1].getItem().getContainerItemStack(inventory[1]);
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
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        NBTTagCompound recipeTag = new NBTTagCompound("recipe");
        if(recipe != null) recipe.writeToNBT(recipeTag);
        nbt.setCompoundTag("recipe", recipeTag);
        nbt.setShort("burnTime", (short) burnTime);
        nbt.setShort("processTime", (short) processTime);
        nbt.setShort("fuelBurnTime", (short) fuelBurnTime);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        recipe = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("recipe"));
        burnTime = nbt.getShort("burnTime");
        processTime = nbt.getShort("processTime");
        recipeNeedTime = 166;
        fuelBurnTime = nbt.getShort("fuelBurnTime");
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int slot) {
        return slot == 0 ? SLOTS_BOTTOM : (slot == 1 ? SLOTS_TOP : SLOTS_OUTPUT);
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack is, int side) {
        if(slot == 2 || slot == 3) return false;

        return isItemValidForSlot(slot, is);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack is, int side) {
        return (slot == 2 || slot == 3);
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
            return TileEntityFurnace.getItemBurnTime(is) + TileEntityFurnace.getItemBurnTime(is)/4;
        }
        return 0;
    }

}
