package num.complexwiring.world;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockPrimaryOre extends ItemBlock {
    public ItemBlockPrimaryOre(int ID) {
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
        return ModuleWorld.primaryOre.getUnlocalizedName() + "." + BlockPrimaryOre.EnumPrimaryOre.VALID[is.getItemDamage()].getUnlocalizedName();
    }
}
