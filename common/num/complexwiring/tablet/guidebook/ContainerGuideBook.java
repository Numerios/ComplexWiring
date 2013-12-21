package num.complexwiring.tablet.guidebook;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import num.complexwiring.core.ExternalItemInventory;

public class ContainerGuidebook extends Container {

    public ContainerGuidebook(InventoryPlayer inv, ExternalItemInventory inventory){
        addPlayerInventory(inv);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                addSlotToContainer(new Slot(inv, col + row * 9 + 9, 28 + col * 18, 140 + row * 18));
            }
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        return true;
    }

    public void addPlayerInventory(InventoryPlayer inv) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlotToContainer(new Slot(inv, col + row * 9 + 9, 28 + col * 18, 140 + row * 18));
            }
        }
        for (int col = 0; col < 9; col++) {
            addSlotToContainer(new Slot(inv, col, 28 + col * 18, 195));
        }
    }
}
