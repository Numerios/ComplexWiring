package num.complexwiring.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public abstract class ItemInventoryBase implements IInventory {
    private final int inventorySize;
    protected ItemStack[] inventory;
    public ItemStack is;
    public NBTTagCompound nbt;

    public ItemInventoryBase(ItemStack is, int inventorySize) {
        this.inventorySize = inventorySize;
        this.is = is;
        this.inventory = new ItemStack[inventorySize];
    }

    @Override
    public int getSizeInventory() {
        return inventorySize;
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
        return null;
    }

    @Override
    public boolean isInvNameLocalized() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openChest() {
    }

    @Override
    public void closeChest() {
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack is) {
        return true;
    }

    @Override
    public void onInventoryChanged() {
        saveContents();
    }

    public void saveContents() {
        if (this.nbt != null) {
            this.is.setTagCompound(nbt);
        }

        writeToNBT(nbt);
    }

    public void writeToNBT(NBTTagCompound nbt) {
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

    public void readToNBT(NBTTagCompound nbt) {
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
