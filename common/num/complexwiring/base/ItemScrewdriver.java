package num.complexwiring.base;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import num.complexwiring.lib.Reference;

import java.util.List;

public class ItemScrewdriver extends Item {
    public IIcon icon;

    public ItemScrewdriver() {
        super();
        setUnlocalizedName(Reference.MOD_ID.toLowerCase() + "base.screwdriver");
        setCreativeTab(ModuleBase.tabCWBase);
    }

    @Override
    public void registerIcons(IIconRegister ir) {
        icon = ir.registerIcon(Reference.TEXTURE_PATH + "screwdriver");
    }

    @Override
    public int getMetadata(int meta) {
        return meta;
    }

    @Override
    public IIcon getIconFromDamage(int meta) {
        return icon;
    }

    @Override
    public String getUnlocalizedName(ItemStack is) {
        return ModuleBase.screwdriver.getUnlocalizedName();
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        list.add(new ItemStack(ModuleBase.screwdriver));
    }

    public void registerOres() {
        for (EnumNugget nugget : EnumNugget.VALID) {
            nugget.registerOre();
        }
    }
}
