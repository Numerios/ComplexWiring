package num.complexwiring.world.industrial.wood;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import num.complexwiring.world.ModuleWorld;

public class ItemBlockWoodIndustrial extends ItemBlock {
    public ItemBlockWoodIndustrial(Block block) {
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
        return ModuleWorld.woodIndustrial.getUnlocalizedName() + "." + EnumWoodIndustrial.VALID[is.getItemDamage()].getUnlocalizedName();
    }
}
