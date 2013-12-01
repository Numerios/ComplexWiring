package num.complexwiring.machine;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import num.complexwiring.api.vec.Vector3;
import num.complexwiring.core.Logger;
import num.complexwiring.core.PacketHandler;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashSet;

public class TileEntityMachineBasic extends TileEntity implements IInventory, ISidedInventory {

    private static final int[] SLOTS_TOP = new int[]{0};
    private static final int[] SLOTS_BOTTOM = new int[]{2, 1};
    private static final int[] SLOTS_SIDE = new int[]{1};
    public final HashSet<EntityPlayer> playersUsing = new HashSet<EntityPlayer>();
    public int currentFuelBurnTime = 0;
    public int machineBurnTime = 0;
    public int machineProcessTime = 0;
    protected ItemStack[] inventory;

    public TileEntityMachineBasic() {
        super();
        inventory = new ItemStack[getSizeInventory()];
    }

    @Override
    public void updateEntity() {
        if (worldObj == null) {
            return;
        }

        if (machineBurnTime > 0) {
            machineBurnTime--;
        }
        if (worldObj.isRemote) {
            Logger.debug("CLIENT - COOK: " + +machineProcessTime + " | BURN " + machineBurnTime);
        }

        if (!worldObj.isRemote) {
            Logger.debug("SERVER - COOK " + machineProcessTime + " | BURN " + machineBurnTime);
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
            PacketHandler.sendPacket(getDescriptionPacket(), worldObj, Vector3.get(this));
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
    public int getSizeInventory() {
        return 3;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inventory[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        if (getStackInSlot(slot) != null) {
            ItemStack is;

            if (getStackInSlot(slot).stackSize <= amount) {
                is = getStackInSlot(slot);
                setInventorySlotContents(slot, null);
                return is;
            } else {
                is = getStackInSlot(slot).splitStack(amount);
                if (getStackInSlot(slot).stackSize == 0) {
                    setInventorySlotContents(slot, null);
                }
                return is;
            }
        } else {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        if (getStackInSlot(slot) != null) {
            ItemStack is = getStackInSlot(slot);
            setInventorySlotContents(slot, null);
            return is;
        } else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack is) {
        inventory[slot] = is;
        if (is != null && is.stackSize > getInventoryStackLimit()) {
            is.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public String getInvName() {
        return ModuleMachine.machineBasic.getLocalizedName();
    }

    @Override
    public boolean isInvNameLocalized() {
        return true;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return Vector3.get(player).distance(Vector3.get(this)) <= 64 && Vector3.get(this).toTile(worldObj) == this;
    }

    @Override
    public void openChest() {
    }

    @Override
    public void closeChest() {
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack is) {
        return true;
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        nbt.setInteger("burnTime", machineBurnTime);
        nbt.setInteger("processTime", machineProcessTime);

        NBTTagList inventoryNBT = new NBTTagList();
        for (int i = 0; i < getSizeInventory(); i++) {
            if (getStackInSlot(i) != null) {
                NBTTagCompound itemNBT = new NBTTagCompound();
                itemNBT.setByte("slot", (byte) i);
                getStackInSlot(i).writeToNBT(itemNBT);
                inventoryNBT.appendTag(itemNBT);
            }
        }
        nbt.setTag("contents", inventoryNBT);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        machineBurnTime = nbt.getInteger("burnTime");
        machineProcessTime = nbt.getInteger("processTime");

        NBTTagList inventoryNBT = nbt.getTagList("contents");
        inventory = new ItemStack[getSizeInventory()];
        for (int i = 0; i < inventoryNBT.tagCount(); i++) {
            NBTTagCompound itemNBT = (NBTTagCompound) inventoryNBT.tagAt(i);
            byte slot = itemNBT.getByte("slot");
            if (slot >= 0 && slot < getSizeInventory()) {
                setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(itemNBT));
            }
        }
    }

    @Override
    public Packet getDescriptionPacket() {
        return PacketHandler.getPacket(this, machineBurnTime, machineProcessTime);
    }

    public void handlePacket(DataInputStream is) throws IOException {
        Logger.debug("HANDLING PACKET!");
        machineBurnTime = is.readInt();
        machineProcessTime = is.readInt();
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
        Logger.debug("-::: PROCESS " + (machineProcessTime * scale / 100));
        return machineProcessTime * scale / 100;
    }

    public int getBurnTimeScaled(int scale) {
        if (currentFuelBurnTime == 0 || !isBurning()) {
            return 0;
        }
        Logger.debug("-::: BURN " + (machineBurnTime * scale / currentFuelBurnTime));
        return machineBurnTime * scale / currentFuelBurnTime;
    }

    public boolean isBurning() {
        return machineBurnTime > 0;
    }
}
