package num.complexwiring.client.slot;

import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import num.complexwiring.api.base.TileEntityInventoryBase;

public class SlotMachine extends Slot {
    protected TileEntityInventoryBase machine;

    public SlotMachine(TileEntityInventoryBase machine, int ID, int x, int y) {
        super(machine, ID, x, y);
        this.machine = machine;
    }

    @Override
    public boolean isItemValid(ItemStack is) {
        return machine.isItemValidForSlot(slotNumber, is);
    }

    @Override
    public int getSlotStackLimit() {
        ItemStack is = machine.getStackInSlot(slotNumber);
        if (is != null) {
            return is.getMaxStackSize();
        }
        return machine.getInventoryStackLimit();
    }
}
