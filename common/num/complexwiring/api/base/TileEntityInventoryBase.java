package num.complexwiring.api.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.Constants;
import num.complexwiring.api.vec.Vector3;

import java.util.HashSet;
import java.util.Set;

/**
 * A basic TileEntity with IInventory support!
 */
public abstract class TileEntityInventoryBase extends TileEntityBase implements IInventory {
    public final Set<EntityPlayer> playersUsing = new HashSet<EntityPlayer>();
    private final int inventorySize;
    protected ItemStack[] inventory;
    private String name;

    public TileEntityInventoryBase(int inventorySize, String name) {
        this.inventorySize = inventorySize;
        this.name = name;
        inventory = new ItemStack[getSizeInventory()];
    }

    @Override
    public void setup() {
    }

    @Override
    public void update() {
        super.update();
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
    public String getInventoryName() {
        return StatCollector.translateToLocal(name + ".name");
    }

    @Override
    public boolean hasCustomInventoryName() {
        return true;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return Vector3.get(player).distance(this.getPosition()) <= 64 && this.getPosition().toTile(worldObj) == this;
    }

    @Override
    public void openInventory() {
    }

    @Override
    public void closeInventory() {
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack is) {
        return true;
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        writePacketNBT(nbt);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        readPacketNBT(nbt);
    }

    protected void writePacketNBT(NBTTagCompound nbt){
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


    protected void readPacketNBT(NBTTagCompound nbt){
        NBTTagList inventoryNBT = nbt.getTagList("contents", Constants.NBT.TAG_COMPOUND);
        inventory = new ItemStack[getSizeInventory()];
        for (int i = 0; i < inventoryNBT.tagCount(); i++) {
            NBTTagCompound itemNBT = inventoryNBT.getCompoundTagAt(i);
            int slot = itemNBT.getByte("slot") & 0xFF;
            if (slot >= 0 && slot < getSizeInventory()) {
                setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(itemNBT));
            }
        }
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        writePacketNBT(nbt);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbt);
    }

    public void onDataPacket(NetworkManager networkManager, S35PacketUpdateTileEntity packet){
        readPacketNBT(packet.func_148857_g());
    }
}
