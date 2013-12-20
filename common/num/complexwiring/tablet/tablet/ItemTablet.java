package num.complexwiring.tablet.tablet;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import num.complexwiring.ComplexWiring;
import num.complexwiring.base.ModuleBase;
import num.complexwiring.core.GuiHandler;
import num.complexwiring.lib.Reference;

import java.util.List;

public class ItemTablet extends Item {
    private Icon icon;

    public ItemTablet(int ID) {
        super(ID);
        setUnlocalizedName(Reference.MOD_ID.toLowerCase() + ".tablet");
        setCreativeTab(ModuleBase.tabCWBase);
        setMaxDamage(1024);
        setMaxStackSize(1);
    }

    @Override
    public void registerIcons(IconRegister ir) {
        icon = ir.registerIcon(Reference.TEXTURE_PATH + this.getUnlocalizedName());
    }

    @Override
    public void getSubItems(int id, CreativeTabs tab, List list) {
        list.add(new ItemStack(this));
    }

    @Override
    public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
        player.openGui(ComplexWiring.instance, GuiHandler.TABLET_BASIC_ID, world, 0, 0, 0);
        return is;
    }
}
