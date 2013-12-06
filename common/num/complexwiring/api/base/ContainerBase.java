package num.complexwiring.api.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public abstract class ContainerBase extends Container{

    public ContainerBase(InventoryPlayer playerInv) {
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int slotID) {
        return null;
    }

    public void onContainerClosed(EntityPlayer player) {
        super.onContainerClosed(player);
    }

    public void addPlayerInventory(InventoryPlayer inv) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlotToContainer(new Slot(inv, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
        for (int col = 0; col < 9; col++) {
            addSlotToContainer(new Slot(inv, col, 8 + col * 18, 142));
        }
    }

}
