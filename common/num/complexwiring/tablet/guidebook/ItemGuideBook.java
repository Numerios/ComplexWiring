package num.complexwiring.tablet.guidebook;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import num.complexwiring.ComplexWiring;
import num.complexwiring.base.ModuleBase;
import num.complexwiring.core.GuiHandler;
import num.complexwiring.lib.Reference;

import java.util.List;

public class ItemGuidebook extends Item{
    private Icon icon;
    private int page;

    public ItemGuidebook(int ID) {
        super(ID);
        setUnlocalizedName(Reference.MOD_ID.toLowerCase() + ".tablet.guidebook");
        setCreativeTab(ModuleBase.tabCWBase);
        setMaxDamage(0);
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
        player.openGui(ComplexWiring.instance, GuiHandler.GUIDEBOOK_ID + getOpenedPage(is), world, 0, 0, 0);
        return is;
    }

    @Override
    public void onCreated(ItemStack is, World world, EntityPlayer player){
        this.setOpenedPage(is, 0);
    }

    public int getOpenedPage(ItemStack is){
        if (is.getTagCompound() == null){
            is.setTagCompound(new NBTTagCompound());
        }
        if (is.getTagCompound().hasKey("page")){
            return is.getTagCompound().getInteger("page");
        }
        return 0;
    }

    public void setOpenedPage(ItemStack is, int page){
        if (is.getTagCompound() == null){
            is.setTagCompound(new NBTTagCompound());
        }
        is.getTagCompound().setInteger("page", page);
    }
}
