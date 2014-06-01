package num.complexwiring.machine.storagebox;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import num.complexwiring.ComplexWiring;
import num.complexwiring.api.prefab.IFacing;
import num.complexwiring.api.prefab.tile.TileEntityInventoryBase;
import num.complexwiring.api.vec.Vector3;
import num.complexwiring.core.Logger;

public class TileStorageBox extends TileEntityInventoryBase implements IStorageBox, IInventory, IFacing {

    private ForgeDirection facing;
    private ItemStack storage;
    private int capacity;

    public TileStorageBox(int capacity, String tileName) {
        super(capacity, tileName);
        Logger.debug(Thread.currentThread().getStackTrace().toString());
        this.capacity = capacity;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void setFacing(ForgeDirection direction) {
        facing = direction;
    }

    public ItemStack add(ItemStack itemStack) {
        if (itemStack != null && itemStack.stackSize > 0) {
            if (storage == null) {
                storage = itemStack;
                return null; //Whole itemStack added to StorageBox
            } else {
                if (storage.isItemEqual(itemStack)) {
                    int finalSize = storage.stackSize + itemStack.stackSize;
                    if (finalSize <= getInventoryStackLimit()) {
                        storage.stackSize += itemStack.stackSize;
                        return null; //Whole itemStack added to StorageBox
                    } else {
                        int overflow = finalSize - getInventoryStackLimit();
                        storage.stackSize = getInventoryStackLimit();
                        return itemStack.splitStack(overflow); // Storage box is full now, returning the rest of itemStack
                    }
                }
            }
        }
        return itemStack;
    }

    public ItemStack remove(int amount) {
        if (storage != null) {
            ItemStack returned;

            if(amount > storage.stackSize) {
                amount = storage.stackSize;
            }
            returned = storage.splitStack(amount);
            if(storage.stackSize < 1) {
                storage = null;
            }

            world().markBlockForUpdate(pos().getX(), pos().getY(), pos().getZ());
            markDirty();
            return returned;
        }
        return null;
    }

    public void removeAndDrop(int amount) {
        ItemStack toDrop = remove(amount);

        if(toDrop != null) {
            Vector3 pos = pos().add(facing.offsetX * .3, facing.offsetY * .3, facing.offsetZ * .3);
            EntityItem entityItem = new EntityItem(world(), pos.getX(), pos.getY(), pos.getZ(), toDrop.splitStack(amount));
            entityItem.delayBeforeCanPickup = 0;
            world().spawnEntityInWorld(entityItem);
        }
    }

    /**
     * Adding items into StorageBox
     * When holding SHIFT key adding 1, otherwise 64
     *
     * @param player
     */
    public void onRightClick(EntityPlayer player) {
        ItemStack itemStack = player.getCurrentEquippedItem();
        if (itemStack != null) {
            if (player.isSneaking()) { //FIXME: Override placing block to allow insert by one
                ItemStack addedItemStack = add(itemStack.splitStack(1)); //If StorageBox is full and player wants to add one more item
                if (addedItemStack != null) {
                    itemStack.stackSize += 1;
                }
                player.inventory.setInventorySlotContents(player.inventory.currentItem, itemStack);
            } else {
                player.inventory.setInventorySlotContents(player.inventory.currentItem, add(itemStack));
            }

            world().markBlockForUpdate(pos().getX(), pos().getY(), pos().getZ());
            markDirty();
        } else {
            if (storage.stackSize < getInventoryStackLimit()) {
                boolean somethingAdded = false;
                for (int slot = 0; slot < player.inventory.getSizeInventory(); slot++) {
                    ItemStack itemInSlot = player.inventory.getStackInSlot(slot);
                    if (itemInSlot != null && itemInSlot.isItemEqual(storage)) {
                        somethingAdded = true;
                        player.inventory.setInventorySlotContents(slot, add(itemInSlot));
                    }
                }

                if (somethingAdded) {
                    world().markBlockForUpdate(pos().getX(), pos().getY(), pos().getZ());
                    ComplexWiring.proxy.updatePlayerInventory(player);
                    markDirty();
                }
            }
        }
    }


    /**
     * Taking items from StorageBox
     * When holding SHIFT key taking by 1, otherwise 64
     *
     * @param player
     */
    public void onLeftClick(EntityPlayer player) {
        int target = 64;
        if (player.isSneaking()) target = 1;

        removeAndDrop(target);
    }

    @Override
    public void writePacketNBT(NBTTagCompound nbt) {
        super.writePacketNBT(nbt);
        nbt.setShort("facing", (short) facing.ordinal());
        if (storage != null) {
            nbt.setTag("containing", storage.writeToNBT(new NBTTagCompound()));
            nbt.setInteger("amount", storage.stackSize);
        } else {
            nbt.setTag("containing", new NBTTagCompound());
            nbt.setInteger("amount", 0);
        }
    }

    @Override
    public void readPacketNBT(NBTTagCompound nbt) {
        super.readPacketNBT(nbt);
        this.facing = ForgeDirection.getOrientation(nbt.getShort("facing"));
        NBTTagCompound content = nbt.getCompoundTag("containing");
        if (!content.hasNoTags()) {
            ItemStack is = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("containing"));
            storage = is;
            storage.stackSize = nbt.getInteger("amount");
        } else {
            storage = null;
        }
    }

    @Override
    public ForgeDirection getFacing() {
        return facing;
    }

    @Override
    public void addToStorage(ItemStack itemStack) {
        add(itemStack);
    }

    @Override
    public int getSizeInventory() {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return storage;
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        ItemStack returnedIs = null;
        if (storage != null) {
            if (storage.stackSize <= amount) {
                returnedIs = storage.copy();
                storage = null;
            } else {
                returnedIs = storage.splitStack(amount);
            }

            world().markBlockForUpdate(pos().getX(), pos().getY(), pos().getZ());
            markDirty();
        }
        return returnedIs;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        return storage;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {
        storage = itemStack;
    }

    @Override
    public int getInventoryStackLimit() {
        if (storage != null) {
            return storage.getMaxStackSize() * capacity;
        }
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return Vector3.get(player).distanceSquared(pos()) <= 1024 && this.pos().toTile(worldObj) == this;
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
