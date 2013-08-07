package num.complexwiring.api;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * IWrapper is an interface for wrappers (things carrying ItemStacks in Item Wires) in Complex Wiring
 */
public interface IWrapper {
    public ItemStack getCarriage();
    public NBTTagCompound getNBT();
    public void loadNBT(NBTTagCompound nbt);
}