package num.complexwiring.machine;

import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import num.complexwiring.api.base.TileEntityInventoryBase;
import num.complexwiring.api.vec.Vector3;
import num.complexwiring.core.PacketHandler;

import java.io.DataInputStream;
import java.io.IOException;

public class TileEntityMachineOrealyzer extends TileEntityInventoryBase implements ISidedInventory {
    private static final int[] SLOTS_TOP = new int[]{0};
    private static final int[] SLOTS_BOTTOM = new int[]{2, 1};
    private static final int[] SLOTS_SIDE = new int[]{1};
    public int currentFuelBurnTime = 0;
    public int machineBurnTime = 0;
    public int machineProcessTime = 0;

    public TileEntityMachineOrealyzer() {
        super(3, EnumMachine.OREALYZER.getFullUnlocalizedName());
    }

    @Override
    public void update() {
        super.update();

        if (machineBurnTime > 0) {
            machineBurnTime--;
        }
        if (worldObj.isRemote) {
        }
        if (!worldObj.isRemote) {
            if (machineBurnTime == 0 && canProcess()) {
                currentFuelBurnTime = machineBurnTime = getFuelBurnTime(getStackInSlot(1));
                if (machineBurnTime > 0) {
                    if (getStackInSlot(1) != null) {
                        inventory[1].stackSize--;
                        if (getStackInSlot(1).stackSize == 0) {
                            inventory[1] = inventory[1].getItem().getContainerItemStack(inventory[1]);
                        }
                    }
                }
            }
            if (isBurning() && canProcess()) {
                machineProcessTime++;
                if (machineProcessTime == 100) {
                    machineProcessTime = 0;
                    process();
                }
            } else {
                machineProcessTime = 0;
            }
            if (ticks % 4 == 0) {
                PacketHandler.sendPacket(getDescriptionPacket(), worldObj, Vector3.get(this));
            }
        }
        //TODO: DO NOT LOAD IT ALL THE TIME!
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        onInventoryChanged();
    }

    private int getFuelBurnTime(ItemStack is) {
        if (is != null) {
            if (is.itemID == Item.coal.itemID) {
                return 200;
            }
        }
        return 0;
    }

    private boolean canProcess() {
        if (getStackInSlot(0) == null) {
            return false;
        } else {
            ItemStack result = MachineBasicRecipes.getOutput(getStackInSlot(0));
            if (result == null) {
                return false;
            }
            if (getStackInSlot(2) == null) {
                return true;
            }
            if (!getStackInSlot(2).isItemEqual(result)) {
                return false;
            }
            return getStackInSlot(2).stackSize + result.stackSize <= getInventoryStackLimit() && getStackInSlot(2).stackSize + result.stackSize <= result.getMaxStackSize();
        }
    }

    public void process() {
        if (canProcess()) {
            ItemStack result = MachineBasicRecipes.getOutput(getStackInSlot(0));
            if (getStackInSlot(2) == null) {
                setInventorySlotContents(2, result.copy());
            } else if (getStackInSlot(2).isItemEqual(result)) {
                //TODO: REWRITE THIS
                inventory[2].stackSize += result.stackSize;
            }

            inventory[0].stackSize--;

            if (getStackInSlot(0).stackSize <= 0) {
                setInventorySlotContents(0, null);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        nbt.setShort("burnTime", (short) machineBurnTime);
        nbt.setShort("processTime", (short) machineProcessTime);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        machineBurnTime = nbt.getShort("burnTime");
        machineProcessTime = nbt.getShort("processTime");
        currentFuelBurnTime = getFuelBurnTime(getStackInSlot(1));
    }

    @Override
    public Packet getDescriptionPacket() {
        return PacketHandler.getPacket(this, EnumMachine.OREALYZER.ordinal());
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
        return slot == 2;
    }

    public int getProcessedTimeScaled(int scale) {
        if (machineProcessTime == 0) {
            return 0;
        }
        return machineProcessTime * scale / 100;
    }

    public int getBurnTimeScaled(int scale) {
        if (machineBurnTime == 0 || !isBurning()) {
            return 0;
        }
        return machineBurnTime * scale / 200;
    }

    public boolean isBurning() {
        return machineBurnTime > 0;
    }
}
