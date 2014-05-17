package num.complexwiring.api.energy;

import net.minecraft.item.ItemStack;

public interface IEnergyItem {
    /**
     * Adds energy to an item
     *
     * @param is       Charged ItemStack
     * @param energy   Energy sent into the item
     * @param simulate If true, charge will be only simulated!
     * @return Amount of energy accepted by item
     */
    public long charge(ItemStack is, long energy, boolean simulate);

    /**
     * Removes energy from an item
     *
     * @param is       Drained ItemStack
     * @param energy   Energy removed from the item
     * @param simulate If true, drainage will be only simulated!
     * @return Amount of energy extracted from item
     */
    public long drain(ItemStack is, long energy, boolean simulate);

    /**
     * Gets the energy currently stored in item
     */
    public long getEnergy(ItemStack is);

    /**
     * Gets the item's capacity (max energy amount)
     */
    public long getCapacity(ItemStack is);
}
