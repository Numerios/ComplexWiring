package num.complexwiring.machine;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerMachineBasic extends Container {
    protected final TileEntityMachineBasic tile;

    public ContainerMachineBasic(InventoryPlayer playerInv, TileEntityMachineBasic tile) {
        this.tile = tile;

        addSlotToContainer(new Slot(tile, 0, 44, 28));
        addSlotToContainer(new Slot(tile, 0, 116, 28));

        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 9; col++){
                addSlotToContainer(new Slot(playerInv, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }

        for(int col = 0; col < 9; col++){
            addSlotToContainer(new Slot(playerInv, col, 8 + col * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        return true;
    }
}
