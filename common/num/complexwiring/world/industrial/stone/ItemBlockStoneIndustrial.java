package num.complexwiring.world.industrial.stone;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import num.complexwiring.world.ModuleWorld;

public class ItemBlockStoneIndustrial extends ItemBlock {
    public ItemBlockStoneIndustrial(Block block) {
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
        return ModuleWorld.stoneIndustrial.getUnlocalizedName() + "." + EnumStoneIndustrial.VALID[is.getItemDamage()].getUnlocalizedName();
    }
}
