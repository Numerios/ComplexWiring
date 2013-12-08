package num.complexwiring.world.ore.decor;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import num.complexwiring.world.ModuleWorld;

public class ItemBlockOreDecor extends ItemBlock {
    public ItemBlockOreDecor(int ID) {
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
        return ModuleWorld.oreDecor.getUnlocalizedName() + "." + EnumOreDecor.VALID[is.getItemDamage()].getUnlocalizedName();
    }
}
