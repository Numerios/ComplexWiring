package num.complexwiring.api.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerBase extends Container{

    protected final TileEntityInventoryBase tile;

    public ContainerBase(InventoryPlayer playerInv, TileEntityInventoryBase tile) {
        this.tile = tile;
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int slotID) {
        return null;
    }

    public boolean canInteractWith(EntityPlayer player) {
        return tile.isUseableByPlayer(player);
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

    public TileEntityInventoryBase getTile(){
        return tile;
    }

}
