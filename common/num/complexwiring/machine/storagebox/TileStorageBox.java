package num.complexwiring.machine.storagebox;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import num.complexwiring.api.prefab.IFacing;
import num.complexwiring.api.prefab.TileEntityInventoryBase;

public class TileStorageBox extends TileEntityInventoryBase implements IStorageBox, IFacing {
    private ItemStack containing;
    private ForgeDirection facing;

    public TileStorageBox(EnumStorageBox storageBox) {
        super(storageBox.capacity, storageBox.getFullUnlocalizedName());
        facing = ForgeDirection.NORTH;
    }

    @Override
    public void update() {
        super.update();
        if (!this.world().isRemote) {
            //    if (ticks % 5 == 0) {
            world().markBlockForUpdate(xCoord, yCoord, zCoord);
            markDirty();
            //    }
        }
    }

    public ItemStack getContaining() {
        if (this.containing == null) {
            this.assembleContaining();
        }
        return this.containing;
    }

    public ForgeDirection getFacing() {
        return this.facing;
    }

    public void setFacing(ForgeDirection facing) {
        this.facing = facing;
    }

    public void assembleContaining() {
        if (world() == null || !world().isRemote) {
            ItemStack is = null;
            boolean refresh = false;

            for (int i = 0; i < this.getSizeInventory(); i++) {
                ItemStack slotIS = this.inventory[i];
                if (slotIS != null && slotIS.stackSize > 0) {
                    if (is == null) {
                        is = slotIS.copy();
                    } else {
                        is.stackSize += slotIS.stackSize;
                    }

                    if (slotIS.stackSize > slotIS.getMaxStackSize()) {
                        refresh = true;
                    }
                }
            }
            if (is != null) {
                this.containing = is.copy();
            }
            if ((refresh || this.inventory.length > this.getSizeInventory()) && this.containing != null) {
                this.refreshInventory(this.containing);
            }
        }
    }

    public void refreshInventory(ItemStack is) {
        this.inventory = new ItemStack[this.getSizeInventory()];
        if (is != null) {
            ItemStack base = is.copy();
            int itemsLeft = base.stackSize;

            for (int slot = 0; slot < this.inventory.length; slot++) {
                int stackL = Math.min(Math.min(itemsLeft, base.getMaxStackSize()), this.getInventoryStackLimit());
                this.inventory[slot] = base.copy();
                this.inventory[slot].stackSize = stackL;
                itemsLeft -= stackL;
                if (base.stackSize <= 0) {
                    base = null;
                    break;
                }
            }
        }
    }

    public void add(ItemStack is, int amount) {
        if (is != null) {
            ItemStack copy = is.copy();
            copy.stackSize = amount;
            this.add(copy);
        }
    }

    public void add(ItemStack is) {
        if (is != null && is.stackSize > 0) {
            if (this.getContaining() == null) {
                this.containing = is;
                refreshInventory(getContaining());
            } else if (this.getContaining().isItemEqual(is)) {
                getContaining().stackSize += is.stackSize;
                refreshInventory(getContaining());
            }
        }
    }

    @Override
    public void writePacketNBT(NBTTagCompound nbt) {
        super.writePacketNBT(nbt);
        nbt.setShort("facing", (short) facing.ordinal());
        this.assembleContaining();
        ItemStack is = this.getContaining();
        if (is != null) {
            nbt.setTag("containing", is.writeToNBT(new NBTTagCompound()));
            nbt.setInteger("amount", is.stackSize);
        }
    }

    @Override
    public void readPacketNBT(NBTTagCompound nbt) {
        super.readPacketNBT(nbt);
        this.facing = ForgeDirection.getOrientation(nbt.getShort("facing"));
        ItemStack is = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("containing"));
        if (is != null && is.getItem() != null && is.stackSize > 0) {
            this.containing = is;
            this.containing.stackSize = nbt.getInteger("amount");
            this.refreshInventory(this.containing);
        }
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        if (containing != null) {
            ItemStack is;
            if (containing.stackSize <= amount) {
                is = containing;
                containing = null;
                this.markDirty();
                world().markBlockForUpdate(xCoord, yCoord, zCoord);
                refreshInventory(getContaining());
                return is;
            } else {
                is = containing.splitStack(amount);
                if (containing.stackSize == 0) {
                    containing = null;
                }
                world().markBlockForUpdate(xCoord, yCoord, zCoord);
                this.markDirty();
                refreshInventory(getContaining());
                return is;
            }
        } else {
            return null;
        }
    }

    @Override
    public void addToStorage(ItemStack is) {
        BlockStorageBox.add(this, is);
    }
}
