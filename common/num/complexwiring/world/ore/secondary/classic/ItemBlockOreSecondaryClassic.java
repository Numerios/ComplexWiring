package num.complexwiring.world.ore.secondary.classic;

import net.minecraft.item.ItemStack;
import num.complexwiring.world.ModuleWorld;
import num.complexwiring.world.ore.secondary.ItemBlockOreSecondary;

public class ItemBlockOreSecondaryClassic extends ItemBlockOreSecondary {
    public ItemBlockOreSecondaryClassic(int ID) {
        super(ID);
    }

    @Override
    public String getUnlocalizedName(ItemStack is) {
        return ModuleWorld.oreSecondaryClassic.getUnlocalizedName() + "." + EnumOreSecondaryClassic.VALID[is.getItemDamage()].getUnlocalizedName();
    }

}
