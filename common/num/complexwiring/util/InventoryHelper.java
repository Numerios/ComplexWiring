package num.complexwiring.util;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import num.complexwiring.api.vec.Vector3;

public class InventoryHelper {
    /**
     * Inserts the ItemStack to an Inventory
     *
     * @param inventory the IInventory inserted in
     * @param is        the ItemStack inserted
     * @return true if success, false if fail
     */
    public static boolean insertToInventory(IInventory inventory, ItemStack is) {
        if (is == null || inventory == null) {
            return false;
        } else {
            for (int slot = 0; slot < inventory.getSizeInventory(); slot++) {
                ItemStack inventoryIS = inventory.getStackInSlot(slot);
                if (inventoryIS == null) {
                    if (inventory.getInventoryStackLimit() >= is.stackSize) {
                        inventory.setInventorySlotContents(slot, is);
                        return true;
                    } else {
                        inventory.setInventorySlotContents(slot, is.splitStack(inventory.getInventoryStackLimit()));
                    }
                } else if (inventoryIS.isItemEqual(is)) {
                    int minStack = Math.min(inventoryIS.getMaxStackSize(), inventory.getInventoryStackLimit());
                    minStack -= inventoryIS.stackSize;
                    if (minStack > 0) {
                        int maxStack = Math.min(minStack, is.stackSize);
                        inventoryIS.stackSize += maxStack;
                        inventory.setInventorySlotContents(slot, inventoryIS);
                        is.stackSize -= maxStack;
                        if (is.stackSize == 0) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Tests if the ItemStack can be inserted to an Inventory HIGHLY
     * EXPERIMENTAL!
     *
     * @param inventory the IInventory checked
     * @param is        the ItemStack checked
     * @return true if the ItemStack is insertable, false if not
     */
    public static boolean canInsertToInventory(IInventory inventory, ItemStack is) {
        if (is == null || inventory == null) {
            return false;
        } else {
            for (int slot = 0; slot < inventory.getSizeInventory(); slot++) {
                ItemStack inventoryIS = inventory.getStackInSlot(slot);
                if (inventoryIS == null) {
                    return true;
                } else if (inventoryIS.isItemEqual(is)) {
                    int minStack = Math.min(inventoryIS.getMaxStackSize(), inventory.getInventoryStackLimit());
                    minStack -= inventoryIS.stackSize;
                    if (minStack > 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Tests if the ItemStack can be inserted to a block
     *
     * @param vec3     the block checked
     * @param is        the ItemStack checked
     * @return true if the ItemStack is insertable, false if not
     */
    public static boolean canInsertToInventory(Vector3 vec3, ItemStack is, World world) {
        if (vec3.toTile(world) instanceof IInventory) {
            return canInsertToInventory((IInventory) vec3.toTile(world), is);
        }
        return false;
    }

    public static boolean extractFromInventory(IInventory inventory, ItemStack is) {
        for (int slot = 0; slot < inventory.getSizeInventory(); slot++) {
            ItemStack inventoryIS = inventory.getStackInSlot(slot);
            if (inventoryIS.getItem() == is.getItem()) {
                if (inventoryIS.stackSize >= is.stackSize) {
                    inventory.decrStackSize(slot, is.stackSize);
                    return true;
                }
            }
        }
        return false;
    }

    public static ItemStack extractFirstFromInventory(IInventory inventory) {
        for (int slot = 0; slot < inventory.getSizeInventory(); slot++) {
            ItemStack inventoryIS = inventory.getStackInSlot(slot);
            if (inventoryIS != null) {
                inventory.decrStackSize(slot, inventoryIS.stackSize);
                return inventoryIS;
            }
        }
        return null;
    }

    public static ItemStack duplicateFirstFromInventory(IInventory inventory) {
        for (int slot = 0; slot < inventory.getSizeInventory(); slot++) {
            ItemStack inventoryIS = inventory.getStackInSlot(slot);
            if (inventoryIS != null) {
                return inventoryIS;
            }
        }
        return null;
    }

    public static ItemStack extractLastFromInventory(IInventory inventory) {
        for (int slot = inventory.getSizeInventory(); slot <= 0; slot--) {
            ItemStack inventoryIS = inventory.getStackInSlot(slot);
            if (inventoryIS != null) {
                inventory.decrStackSize(slot, inventoryIS.stackSize);
                return inventoryIS;
            }
        }
        return null;
    }

    public static boolean canExtractLastFromInventory(IInventory inventory) {
        for (int slot = inventory.getSizeInventory(); slot <= 0; slot--) {
            ItemStack inventoryIS = inventory.getStackInSlot(slot);
            if (inventoryIS != null) {
                return true;
            }
        }
        return false;
    }

    public static boolean canExtractFirstFromInventory(IInventory inventory) {
        for (int slot = 0; slot < inventory.getSizeInventory(); slot++) {
            ItemStack inventoryIS = inventory.getStackInSlot(slot);
            if (inventoryIS != null) {
                return true;
            }
        }
        return false;
    }

    public static boolean canExtractFromInventory(IInventory inventory, ItemStack is) {
        for (int slot = 0; slot < inventory.getSizeInventory(); slot++) {
            ItemStack inventoryIS = inventory.getStackInSlot(slot);
            if (inventoryIS.getItem() == is.getItem()) {
                if (inventoryIS.stackSize >= is.stackSize) {
                    return true;
                }
            }
        }
        return false;
    }
}
