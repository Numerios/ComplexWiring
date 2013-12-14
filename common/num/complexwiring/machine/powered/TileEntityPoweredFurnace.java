package num.complexwiring.machine.powered;

import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import num.complexwiring.api.base.TileEntityPoweredBase;
import num.complexwiring.api.vec.Vector3;
import num.complexwiring.core.InventoryHelper;
import num.complexwiring.core.PacketHandler;

import java.io.DataInputStream;
import java.io.IOException;

public class TileEntityPoweredFurnace extends TileEntityPoweredBase implements ISidedInventory {

    private static final int[] SLOTS_OUTPUT = new int[]{2, 3};
    private static final int[] SLOTS_TOP = new int[]{0};
    private static final int[] SLOTS_BOTTOM = new int[]{2, 3, 1};

    private static final float USED_ENERGY = 1.5F;

    private ItemStack recipe;
    private int processTime = 0;
    private int recipeNeededPower = 160;

    public TileEntityPoweredFurnace() {
        super(4, EnumPoweredMachine.FURNACE.getFullUnlocalizedName());
    }

    @Override
    public void setup() {
        super.setup();
    }

    @Override
    public void update() {
        super.update();

        if (!worldObj.isRemote) {
            storedEnergy = powerHandler.getEnergyStored();

            if(recipe == null) {
                if(FurnaceRecipes.smelting().getSmeltingResult(inventory[0]) != null) {
                    recipe = FurnaceRecipes.smelting().getSmeltingResult(inventory[0]).copy();

                    if(recipeNeededPower <= ((int) storedEnergy)){
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
                if(storedEnergy > 0){
                    powerHandler.useEnergy(USED_ENERGY, USED_ENERGY, true);
                    storedEnergy = powerHandler.getEnergyStored();
                    processTime++;

                    if(processTime >= recipeNeededPower) {
                        endProcessing();
                    }
                } else {
                    recipe = null;
                    recipeNeededPower = 0;
                    processTime = 0;
                }
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
        if (recipe != null && recipe.stackSize != 0) {
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

    @Override
    public Packet getDescriptionPacket() {
        return PacketHandler.getPacket(this, EnumPoweredMachine.FURNACE.ordinal());
    }

    @Override
    public void handlePacket(DataInputStream is) throws IOException {
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        NBTTagCompound recipeTag = new NBTTagCompound("recipe");
        if(recipe != null) recipe.writeToNBT(recipeTag);
        nbt.setCompoundTag("recipe", recipeTag);
        nbt.setShort("processTime", (short) processTime);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        recipe = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("recipe"));
        processTime = nbt.getShort("processTime");
        recipeNeededPower = 160;
    }

    public int getProcessedTimeScaled(int scale) {
        if (processTime == 0 || recipeNeededPower == 0) {
            return 0;
        }
        return processTime * scale / recipeNeededPower;
    }

    public int getEnergyScaled(int scale) {
        if (storedEnergy == 0) {
            return 0;
        }
        return ((int) storedEnergy) * scale / MAX_STORED_ENERGY;
    }

    public int getStoredEnergy() {
        return (int) storedEnergy;
    }
}
