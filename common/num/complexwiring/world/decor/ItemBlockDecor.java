package num.complexwiring.world.decor;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import num.complexwiring.world.ModuleWorld;

public class ItemBlockDecor extends ItemBlock {
    public ItemBlockDecor(int ID) {
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
        return ModuleWorld.decor.getUnlocalizedName() + "." + EnumDecor.VALID[is.getItemDamage()].getUnlocalizedName();
    }
}
