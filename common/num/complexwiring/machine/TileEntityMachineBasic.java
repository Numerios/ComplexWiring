package num.complexwiring.machine;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import num.complexwiring.api.vec.Vector3;

public class TileEntityMachineBasic extends TileEntity implements IInventory {

    public boolean hasWork = false;
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
    }

    @Override
    public int getSizeInventory() {
        return 2;
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
        return Vector3.get(player).distance(Vector3.get(this)) <= 64 && Vector3.get(this).toTile(worldObj) != this;
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
}
