package num.complexwiring.base;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import num.complexwiring.lib.Reference;

import java.util.List;

public class ItemNugget extends Item {
    public ItemNugget() {
        super();
        setHasSubtypes(true);
        setMaxDamage(0);
        setUnlocalizedName(Reference.MOD_ID.toLowerCase() + ".base.nugget");
        setCreativeTab(ModuleBase.tabCWBase);
    }

    @Override
    public void registerIcons(IIconRegister ir) {
        for (EnumNugget nugget : EnumNugget.VALID) {
            nugget.registerIcon(ir);
        }
    }

    @Override
    public int getMetadata(int meta) {
        return meta;
    }

    @Override
    public IIcon getIconFromDamage(int meta) {
        return EnumNugget.VALID[meta].icon;
    }

    @Override
    public String getUnlocalizedName(ItemStack is) {
        return EnumNugget.VALID[is.getItemDamage()].getUnlocalizedName();
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < EnumNugget.VALID.length; i++) {
            list.add(EnumNugget.VALID[i].getIS(1));
        }
    }

    public void registerOres() {
        for (EnumNugget nugget : EnumNugget.VALID) {
            nugget.registerOre();
        }
    }
}
