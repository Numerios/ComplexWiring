package num.complexwiring.world.ore.primary;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import num.complexwiring.world.ModuleWorld;

public class ItemBlockOrePrimary extends ItemBlock {
    public ItemBlockOrePrimary(int ID) {
        super(ID);
        setHasSubtypes(true);
        setMaxDamage(0);
    }

    @Override
    public int getMetadata(int i) {
        return i;
    }

    @Override
    public String getUnlocalizedName(ItemStack is) {
        return ModuleWorld.orePrimary.getUnlocalizedName() + "." + EnumOrePrimary.VALID[is.getItemDamage()].getUnlocalizedName();
    }
}
