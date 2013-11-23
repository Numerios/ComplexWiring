package num.complexwiring.machine;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import num.complexwiring.client.SlotMachine;

public class ContainerMachineBasic extends Container {
    protected final TileEntityMachineBasic tile;

    public ContainerMachineBasic(InventoryPlayer playerInv, TileEntityMachineBasic tile) {
        this.tile = tile;

        addSlotToContainer(new SlotMachine(tile, 0, 44, 28));
        addSlotToContainer(new SlotMachine(tile, 1, 116, 28));

        addPlayerInventory(playerInv);
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

            if (slotID <= 1) {
                if (!mergeItemStack(slotIS, 2, inventorySlots.size(), true)) {
                    return null;
                }
            } else {
                if (!mergeItemStack(slotIS, 0, 2, false)) {
                    return null;
                }
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

}
