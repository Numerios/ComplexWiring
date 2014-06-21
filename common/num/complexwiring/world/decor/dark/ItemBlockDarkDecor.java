package num.complexwiring.world.decor.dark;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import num.complexwiring.world.ModuleWorld;

public class ItemBlockDarkDecor extends ItemBlock {
    public ItemBlockDarkDecor(Block block) {
        super(block);
        setHasSubtypes(true);
        setMaxDamage(0);
    }

    @Override
    public int getMetadata(int i) {
        return i;
    }

    @Override
    public String getUnlocalizedName(ItemStack is) {
        return ModuleWorld.decorDark.getUnlocalizedName() + "." + EnumDarkDecor.VALID[is.getItemDamage()].getUnlocalizedName();
    }
}
