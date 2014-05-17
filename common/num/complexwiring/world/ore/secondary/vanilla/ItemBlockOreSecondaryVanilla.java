package num.complexwiring.world.ore.secondary.vanilla;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import num.complexwiring.world.ModuleWorld;
import num.complexwiring.world.ore.secondary.ItemBlockOreSecondary;

public class ItemBlockOreSecondaryVanilla extends ItemBlockOreSecondary {
    public ItemBlockOreSecondaryVanilla(Block block) {
        super(block);
    }

    @Override
    public String getUnlocalizedName(ItemStack is) {
        return ModuleWorld.oreSecondaryVanilla.getUnlocalizedName() + "." + EnumOreSecondaryVanilla.VALID[is.getItemDamage()].getUnlocalizedName();
    }

}
