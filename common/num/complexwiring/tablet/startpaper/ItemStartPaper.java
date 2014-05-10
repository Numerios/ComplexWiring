package num.complexwiring.tablet.startpaper;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import num.complexwiring.ComplexWiring;
import num.complexwiring.base.ModuleBase;
import num.complexwiring.core.GuiHandler;
import num.complexwiring.lib.Reference;

import java.util.List;

public class ItemStartPaper extends Item {
    private IIcon icon;

    public ItemStartPaper() {
        super();
        setUnlocalizedName(Reference.MOD_ID.toLowerCase() + ".tablet.startpaper");
        setCreativeTab(ModuleBase.tabCWBase);
        setMaxDamage(1024);
        setMaxStackSize(1);
    }

    @Override
    public void registerIcons(IIconRegister ir) {
        icon = ir.registerIcon(Reference.TEXTURE_PATH + this.getUnlocalizedName());
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        list.add(new ItemStack(this));
    }

    @Override
    public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
        player.openGui(ComplexWiring.instance, GuiHandler.STARTPAPER_ID, world, 0, 0, 0);
        return is;
    }
}
