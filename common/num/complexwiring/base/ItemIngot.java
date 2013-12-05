package num.complexwiring.base;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import num.complexwiring.ComplexWiring;
import num.complexwiring.lib.Reference;
import num.complexwiring.world.EnumOrePrimary;

import java.util.List;

public class ItemIngot extends Item{

    public ItemIngot(int itemId) {
        super(itemId);
        setUnlocalizedName(Reference.MOD_ID.toLowerCase() + ".base.item");
        setCreativeTab(ComplexWiring.tabCW);
    }

    @Override
    public void registerIcons(IconRegister ir) {
        for (EnumIngot ingot : EnumIngot.VALID) {
            ingot.registerIcon(ir);
        }
    }

    @Override
    public int getMetadata(int meta) {
        return meta;
    }

    @Override
    public Icon getIconFromDamage(int meta) {
        return EnumIngot.VALID[meta].icon;

    }

    @Override
    public String getUnlocalizedName(ItemStack is) {
        int meta = is.getItemDamage();
        return EnumIngot.VALID[meta].getUnlocalizedName();
    }

    @Override
    public void getSubItems(int id, CreativeTabs tab, List list) {
        for (int i = 0; i < EnumIngot.values().length; i++) {
            list.add(new ItemStack(id, 1, i));
        }
    }

    public void registerOres() {
        for (EnumIngot ingot: EnumIngot.VALID) {
            ingot.registerOre();
        }
    }

}
