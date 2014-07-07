package num.complexwiring.mechanical.storagebox;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import num.complexwiring.ComplexWiring;
import num.complexwiring.api.prefab.IFacing;
import num.complexwiring.api.prefab.tile.TileEntityInventoryBase;
import num.complexwiring.base.ItemScrewdriver;

public abstract class TileStorageBox extends TileEntityInventoryBase implements IStorageBox, IFacing {
    private ItemStack containing;
    private ForgeDirection facing;

    public TileStorageBox(int capacity, String tileName) {
        super(capacity, tileName);
    }

    @Override
    public void update() {
        super.update();
        if (!this.worldObj.isRemote) {
            if (ticks % 10 == 0) {
                if (getAmountInv() == 0) {
                    containing = null;
                }
            }
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            markDirty();
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

    public void onLeftClick(EntityPlayer player) {
        if (getContaining() == null) {
            return;
        }
        int target;
        if (!world().isRemote) {
            if (player.isSneaking()) {
                target = 1;
            } else {
                target = 64;      //maxStackSize = ejectAll
            }
            int ejected = 0;
            if (getContaining() != null && target > 0) {
                for (int slot = 0; slot < getSizeInventory(); slot++) {
                    ItemStack slotIS = getStackInSlot(slot);
                    if (slotIS != null && slotIS.stackSize > 0) {
                        int taken = Math.min(slotIS.stackSize, target);
                        ItemStack dropped = slotIS.copy();
                        dropped.stackSize = taken;

                        EntityItem entityItem = new EntityItem(world(), player.posX, player.posY, player.posZ, dropped);
                        entityItem.delayBeforeCanPickup = 0;
                        world().spawnEntityInWorld(entityItem);

                        slotIS.stackSize -= taken;
                        ejected += taken;
                        if (slotIS.stackSize <= 0) {
                            slotIS = null;
                        }
                        setInventorySlotContents(slot, slotIS);
                        if (getAmountInv() == 0) {
                            containing = null;
                        }
                        if (ejected >= target) {
                            return;
                        }
                    }
                }
                world().markBlockForUpdate(xCoord, yCoord, zCoord);
                markDirty();
            }
        }
    }

    public void onRightClick(EntityPlayer player) { //FIXME: Override placing blocks when sneaking to allow inserting by one
        ItemStack is = player.getCurrentEquippedItem();
        if (is != null) {
            if (is.getItem() instanceof ItemScrewdriver) {
                this.setFacing(ItemScrewdriver.getSideToRotate(player.rotationYaw, 0F, !player.isSneaking()));
                player.swingItem();
                is.damageItem(1, player);
                return;
            }

            if ((getContaining() != null && containing.isItemEqual(is)) || getContaining() == null) {
                player.inventory.setInventorySlotContents(player.inventory.currentItem, addChecked(is));
            }
        } else {
            if (inventory[0] != null && getAmountInv() < getInventoryStackLimit()*getSizeInventory()) {
                boolean somethingAdded = false;
                for (int slot = 0; slot < player.inventory.getSizeInventory(); slot++) {
                    ItemStack itemInSlot = player.inventory.getStackInSlot(slot);
                    if (itemInSlot != null && itemInSlot.isItemEqual(inventory[0])) {
                        somethingAdded = true;
                        player.inventory.setInventorySlotContents(slot, addChecked(itemInSlot));
                    }
                }

                if (somethingAdded) {
                    world().markBlockForUpdate(xCoord, yCoord, zCoord);
                    ComplexWiring.proxy.updatePlayerInventory(player);
                    markDirty();
                }
            }
        }
    }

    public int getAmountInv() {   //this be an ugly hax
        int amount = 0;
        for (int i = 0; i < this.getSizeInventory(); i++) {
            ItemStack slotIS = getStackInSlot(i);
            if (slotIS != null)
                amount += slotIS.stackSize;
        }
        return amount;
    }

    public ItemStack addChecked(ItemStack is) {
        if (is == null) {
            return is;
        }
        if (getContaining() == null || getContaining().isItemEqual(is)) {
            int free = Math.max((getSizeInventory() * 64) - (containing != null ? containing.stackSize : 0), 0);
            if (is.stackSize <= free) {
                this.add(is);
                is = null;
            } else {
                this.add(is, free);
                is.stackSize -= free;
            }
            return is;
        }
        if (is.stackSize <= 0) {
            return null;
        }
        return is;
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
        } else {
            nbt.setTag("containing", new NBTTagCompound());
            nbt.setInteger("amount", 0);
        }
    }

    @Override
    public void readPacketNBT(NBTTagCompound nbt) {
        super.readPacketNBT(nbt);
        this.facing = ForgeDirection.getOrientation(nbt.getShort("facing"));
        NBTTagCompound nbtContaining = nbt.getCompoundTag("containing");
        if (!nbtContaining.hasNoTags()) {
            this.containing = ItemStack.loadItemStackFromNBT(nbtContaining);
            this.containing.stackSize = nbt.getInteger("amount");
            this.refreshInventory(this.containing);
        } else {
            containing = null;
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
                refreshInventory(null);
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
        this.addChecked(is);
    }

    public boolean isEmpty() {
        return containing == null;
    }

    public static class TileStorageBoxBasic extends TileStorageBox {
        public TileStorageBoxBasic() {
            super(EnumStorageBox.BASIC.capacity, EnumStorageBox.BASIC.getFullUnlocalizedName());
        }
    }

    public static class TileStorageBoxAdvanced extends TileStorageBox {
        public TileStorageBoxAdvanced() {
            super(EnumStorageBox.ADVANCED.capacity, EnumStorageBox.ADVANCED.getFullUnlocalizedName());
        }
    }
}