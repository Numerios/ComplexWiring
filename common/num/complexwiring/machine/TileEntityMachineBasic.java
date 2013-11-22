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

    public TileEntityMachineBasic(){
        super();
        inventory = new ItemStack[31];
    }
    @Override
    public void updateEntity() {

    }

    @Override
    public int getSizeInventory() {
        return 31;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inventory[slot];
    }

    @Override
    public ItemStack decrStackSize(int i, int j) {
        return null;   //TODO STUFF
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack is) {
    }

    @Override
    public String getInvName() {
        return "Basic Machine";
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
        return Vector3.get(player).distance(Vector3.get(this)) <= 64;
    }

    @Override
    public void openChest() {
    }

    @Override
    public void closeChest() {
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack is) {
        return false;
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt){
        super.writeToNBT(nbt);

        NBTTagList inventoryNBT = new NBTTagList();
        for (int i = 0; i < inventory.length; i++){
            if(inventory[i] != null){
                NBTTagCompound itemNBT = new NBTTagCompound();
                itemNBT.setByte("slot", (byte) i);
                inventory[i].writeToNBT(itemNBT);
                inventoryNBT.appendTag(itemNBT);
            }
        }
        nbt.setTag("contents", inventoryNBT);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt){
        super.readFromNBT(nbt);

        inventory = new ItemStack[31];
        NBTTagList inventoryNBT = new NBTTagList();
        for (int i = 0; i < inventoryNBT.tagCount(); i++){
            NBTTagCompound itemNBT = (NBTTagCompound) inventoryNBT.tagAt(i);
            byte slot = itemNBT.getByte("slot");
            if(slot >= 0 && slot < inventory.length){
                inventory[slot] = ItemStack.loadItemStackFromNBT(itemNBT);
            }
        }
    }
}
