package num.complexwiring.base;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import num.complexwiring.lib.Reference;

import java.util.List;

public class ItemDust extends Item {
    public ItemDust() {
        super();
        setUnlocalizedName(Reference.MOD_ID.toLowerCase() + ".base.dust");
        setCreativeTab(ModuleBase.tabCWBase);
    }

    @Override
    public void registerIcons(IIconRegister ir) {
        for (EnumIngot ingot : EnumIngot.VALID) {
            ingot.registerIcon(ir);
        }
    }

    @Override
    public int getMetadata(int meta) {
        return meta;
    }

    @Override
    public IIcon getIconFromDamage(int meta) {
        return EnumDust.VALID[meta].icon;
    }

    @Override
    public String getUnlocalizedName(ItemStack is) {
        return EnumDust.VALID[is.getItemDamage()].getUnlocalizedName();
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < EnumDust.VALID.length; i++) {
            list.add(EnumDust.VALID[i].getIS(1));
        }
    }

    public void registerOres() {
        for (EnumDust ingot : EnumDust.VALID) {
            ingot.registerOre();
        }
    }

}
