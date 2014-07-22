package num.complexwiring.mechanical.template;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import num.complexwiring.base.ModuleBase;
import num.complexwiring.lib.Reference;
import num.complexwiring.mechanical.ModuleMechanical;

import java.util.List;

public class ItemTemplate extends Item {
    public IIcon icon_empty, icon_full;

    public ItemTemplate() {
        super();
        setHasSubtypes(true);
        setMaxDamage(0);
        setUnlocalizedName(Reference.MOD_ID.toLowerCase() + ".mechanical.template");
        setCreativeTab(ModuleBase.tabCWBase);
    }

    @Override
    public void onCreated(ItemStack is, World world, EntityPlayer player) {
        is.stackTagCompound = new NBTTagCompound();
        is.stackTagCompound.setBoolean("isEmpty", true);
        super.onCreated(is, world, player);
    }

    @Override
    public void registerIcons(IIconRegister ir) {
        icon_empty = ir.registerIcon(Reference.TEXTURE_PATH + "template_empty");
        icon_full = ir.registerIcon(Reference.TEXTURE_PATH + "template_full");
    }

    @Override
    public int getMetadata(int meta) {
        return meta;
    }

    @Override
    public IIcon getIconFromDamage(int meta) {
        return icon_empty;
    }

   /* @Override
    public IIcon getIcon(ItemStack is, int pass) {
        if (!isEmpty(is)) {
            return icon_full;
        }
        return icon_empty;
    }                   */

    @Override
    public String getUnlocalizedName(ItemStack is) {
        return ModuleMechanical.template.getUnlocalizedName();
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        list.add(createEmpty());
        list.add(create(new ItemStack(Items.diamond)));
    }

    private static boolean hasNBT(ItemStack is) {
        return is.stackTagCompound != null;
    }

    public static boolean isEmpty(ItemStack template) {
        return hasNBT(template) && template.stackTagCompound.getBoolean("isEmpty");
    }

    public static boolean setContents(ItemStack template, ItemStack contents) {
        if (!isEmpty(template)) {
            return false;
        }
        NBTTagCompound contentsTag = new NBTTagCompound();
        contents.writeToNBT(contentsTag);
        template.stackTagCompound.setTag("contents", contentsTag);
        template.stackTagCompound.setBoolean("isEmpty", false);
        return true;
    }

    public static ItemStack getContents(ItemStack template) {
        if (isEmpty(template)) {
            return null;
        }
        NBTTagCompound contentsTag = (NBTTagCompound) template.stackTagCompound.getTag("contents");
        return ItemStack.loadItemStackFromNBT(contentsTag);
    }

    public static ItemStack createEmpty() {
        ItemStack template = new ItemStack(ModuleMechanical.template);
        template.stackTagCompound = new NBTTagCompound();
        template.stackTagCompound.setBoolean("isEmpty", true);
        return template;
    }

    public static ItemStack create(ItemStack contents) {
        ItemStack template = createEmpty();
        setContents(template, contents);
        return template;
    }

    @Override
    public void addInformation(ItemStack is, EntityPlayer player, List list, boolean b) {
        if (isEmpty(is)) {
            list.add("EMPTY");
        } else {
            list.add("Contents:");
            ItemStack contents = getContents(is);
            if (contents != null) {
                list.add("" + getContents(is).toString());
            }
        }
        super.addInformation(is, player, list, b);
    }
}
