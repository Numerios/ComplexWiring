package num.complexwiring.machine;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import num.complexwiring.client.SlotMachine;
import num.complexwiring.client.SlotOutput;
import num.complexwiring.world.ModuleWorld;

public class ContainerOrelyzer extends Container {
    protected final TileEntityOrelyzer tile;

    public ContainerOrelyzer(InventoryPlayer playerInv, TileEntityOrelyzer tile) {
        this.tile = tile;

        addSlotToContainer(new SlotMachine(tile, 0, 56, 17));   // ore input
        addSlotToContainer(new SlotMachine(tile, 1, 56, 53));   // fuel input
        addSlotToContainer(new SlotOutput(tile, 2, 116, 35));   // ore output
        addSlotToContainer(new SlotOutput(tile, 3, 136, 35));   // ore output 2


        addPlayerInventory(playerInv);

        tile.playersUsing.add(playerInv.player);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tile.isUseableByPlayer(player);
    }

    public void addPlayerInventory(InventoryPlayer inv) {
        //inventory - 9-26
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlotToContainer(new Slot(inv, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }

        //hotbar - 0-9
        for (int col = 0; col < 9; col++) {
            addSlotToContainer(new Slot(inv, col, 8 + col * 18, 142));
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotID) {
        ItemStack is = null;
        Slot slot = (Slot) inventorySlots.get(slotID);

        if (slot != null && slot.getHasStack()) {
            ItemStack slotIS = slot.getStack();
            is = slotIS.copy();

            if (slotID == 2) {
                if (!mergeItemStack(slotIS, 4, 40, true)) {
                    return null;
                }
                slot.onSlotChange(slotIS, is);
            } else if (slotID >= 3) {
                if (slotIS.itemID == ModuleWorld.orePrimary.blockID) {
                    if (!mergeItemStack(slotIS, 0, 1, false)) {
                        return null;
                    }
                } else if (slotIS.itemID == Item.coal.itemID) {
                    if (!mergeItemStack(slotIS, 1, 2, false)) {
                        return null;
                    }
                } else if (slotID >= 4 && slotID < 31) {
                    if (!mergeItemStack(slotIS, 31, 40, false)) {
                        return null;
                    }
                } else if (slotID >= 31 && slotID < 40 && !mergeItemStack(slotIS, 4, 31, false)) {
                    return null;
                }
            } else if (!mergeItemStack(slotIS, 4, 40, false)) {
                return null;
            }

            if (slotIS.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }

            if (slotIS.stackSize == is.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(player, slotIS);

        }
        return is;
    }

    @Override
    public void onContainerClosed(EntityPlayer player) {
        super.onContainerClosed(player);
        tile.playersUsing.remove(player);
    }

}
