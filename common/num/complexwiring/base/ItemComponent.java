package num.complexwiring.base;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import num.complexwiring.lib.Reference;

import java.util.List;

public class ItemComponent extends Item {
    public ItemComponent() {
        super();
        setHasSubtypes(true);
        setMaxDamage(0);
        setUnlocalizedName(Reference.MOD_ID.toLowerCase() + ".base.component");
        setCreativeTab(ModuleBase.tabCWBase);
    }

    @Override
    public void registerIcons(IIconRegister ir) {
        for (EnumComponent component : EnumComponent.VALID) {
            component.registerIcon(ir);
        }
    }

    @Override
    public int getMetadata(int meta) {
        return meta;
    }

    @Override
    public IIcon getIconFromDamage(int meta) {
        return EnumComponent.VALID[meta].icon;
    }

    @Override
    public String getUnlocalizedName(ItemStack is) {
        return EnumComponent.VALID[is.getItemDamage()].getUnlocalizedName();
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < EnumComponent.VALID.length; i++) {
            list.add(EnumComponent.VALID[i].getIS(1));
        }
    }

    public void registerOres() {
        for (EnumComponent component : EnumComponent.VALID) {
            component.registerOre();
        }
    }
}
