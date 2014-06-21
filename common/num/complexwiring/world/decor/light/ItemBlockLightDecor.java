package num.complexwiring.world.decor.light;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import num.complexwiring.world.ModuleWorld;

public class ItemBlockLightDecor extends ItemBlock {
    public ItemBlockLightDecor(Block block) {
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
        return ModuleWorld.decorLight.getUnlocalizedName() + "." + EnumLightDecor.VALID[is.getItemDamage()].getUnlocalizedName();
    }
}
