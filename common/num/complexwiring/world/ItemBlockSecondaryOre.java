package num.complexwiring.world;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockSecondaryOre extends ItemBlock {
    public ItemBlockSecondaryOre(int ID) {
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
        return ModuleWorld.secondaryOre.getUnlocalizedName() + "." + BlockSecondaryOre.EnumSecondaryOre.VALID[is.getItemDamage()].getUnlocalizedName();
    }
}
