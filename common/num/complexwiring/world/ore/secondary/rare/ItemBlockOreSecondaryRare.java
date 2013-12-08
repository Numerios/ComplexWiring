package num.complexwiring.world.ore.secondary.rare;

import net.minecraft.item.ItemStack;
import num.complexwiring.world.ModuleWorld;
import num.complexwiring.world.ore.secondary.ItemBlockOreSecondary;

public class ItemBlockOreSecondaryRare extends ItemBlockOreSecondary {
    public ItemBlockOreSecondaryRare(int ID) {
        super(ID);
    }

    @Override
    public String getUnlocalizedName(ItemStack is) {
        return ModuleWorld.oreSecondaryRare.getUnlocalizedName() + "." + EnumOreSecondaryRare.VALID[is.getItemDamage()].getUnlocalizedName();
    }

}
