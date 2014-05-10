package num.complexwiring.client.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotSpecific extends Slot {
    private final ItemStack specific;
    private final boolean canTake;

    public SlotSpecific(IInventory inventory, ItemStack is, boolean canTake, int ID, int x, int y) {
        super(inventory, ID, x, y);
        this.specific = is;
        this.canTake = canTake;
    }

    @Override
    public boolean isItemValid(ItemStack is) {
        return specific.getItem().equals(is);
    }

    @Override
    public int getSlotStackLimit() {
        return Math.max(specific.getMaxStackSize(), 64); //sanity check
    }

    @Override
    public boolean canTakeStack(EntityPlayer player) {
        return canTake;
    }

}
