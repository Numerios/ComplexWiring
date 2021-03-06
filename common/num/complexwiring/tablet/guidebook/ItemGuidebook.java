package num.complexwiring.tablet.guidebook;

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

public class ItemGuidebook extends Item {
    private IIcon icon;

    public ItemGuidebook() {
        super();
        setUnlocalizedName(Reference.MOD_ID.toLowerCase() + ".tablet.guidebook");
        setCreativeTab(ModuleBase.tabCWBase);
        setMaxDamage(0);
        setMaxStackSize(1);
    }

    @Override
    public void registerIcons(IIconRegister ir) {
        icon = ir.registerIcon(Reference.TEXTURE_PATH + this.getUnlocalizedName());
    }

    @Override
    @SuppressWarnings("unchecked")
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        list.add(new ItemStack(this));
    }

    @Override
    public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
        player.openGui(ComplexWiring.instance, GuiHandler.GUIDEBOOK_ID, world, 0, 0, 0);
        return is;
    }

    @Override
    public void onCreated(ItemStack is, World world, EntityPlayer player) {
    }
}
