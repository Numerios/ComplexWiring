package num.complexwiring.block;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import num.complexwiring.api.IWrapper;

public class ItemWrapper implements IWrapper {
    // Wrapper = Item carrying ItemStacks :)
    private ItemStack carriage;
    
    public ItemWrapper(ItemStack is){
        this.carriage = is;
    } 

    @Override
    public ItemStack getCarriage() {
        return carriage;
    }

    @Override
    public NBTTagCompound getNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        carriage.writeToNBT(nbt);
        return nbt;
    }

    @Override
    public void loadNBT(NBTTagCompound nbt) {
        carriage.readFromNBT(nbt);
    }
    
}
