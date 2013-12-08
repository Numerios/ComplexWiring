package num.complexwiring.world.ore.secondary.vanilla;

import net.minecraft.item.ItemStack;
import num.complexwiring.world.ModuleWorld;
import num.complexwiring.world.ore.secondary.ItemBlockOreSecondary;

public class ItemBlockOreSecondaryVanilla extends ItemBlockOreSecondary{

    public ItemBlockOreSecondaryVanilla(int ID) {
        super(ID);
    }

    @Override
    public String getUnlocalizedName(ItemStack is) {
        return ModuleWorld.oreSecondaryVanilla.getUnlocalizedName() + "." + BlockOreSecondaryVanilla.oreList.get(is.getItemDamage()).getUnlocalizedName();
    }

}
