package num.complexwiring.mechanical.template;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import num.complexwiring.base.ModuleBase;
import num.complexwiring.lib.Reference;
import num.complexwiring.lib.Strings;
import num.complexwiring.mechanical.ModuleMechanical;

import java.util.List;

public class ItemTemplate extends Item {
    public IIcon icon_empty, icon_full;

    public ItemTemplate() {
        super();
        setHasSubtypes(true);
        setMaxDamage(0);
        setMaxStackSize(1);
        setUnlocalizedName(Reference.MOD_ID.toLowerCase() + ".mechanical.template");
        setCreativeTab(ModuleBase.tabCWBase);
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

    @Override
    public IIcon getIcon(ItemStack is, int pass) {
        if (hasContents(is)) {
            return icon_full;
        }
        return icon_empty;
    }

    @Override
    public String getUnlocalizedName(ItemStack is) {
        return ModuleMechanical.template.getUnlocalizedName();
    }

    @Override
    public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
        if (player.isSneaking()) {
            if (hasContents(is)) {
                ItemStack contents = getContents(is, true);
                if (contents != null) {
                    EntityItem item = new EntityItem(world, player.posX + 0.5D, player.posY + 0.5D, player.posZ + 0.5D, contents);
                    world.spawnEntityInWorld(item);
                }
            }
        }
        return super.onItemRightClick(is, world, player);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        ItemStack empty = create();
        list.add(empty);
        ItemStack diamond = create();
        setContents(diamond, new ItemStack(Items.diamond));
        list.add(diamond);
    }

    @Override
    public void addInformation(ItemStack is, EntityPlayer player, List list, boolean b) {
        list.add(Strings.TEMPLATE_CONTENTS + ":");
        if (hasContents(is)) {
            ItemStack contents = getContents(is, false);
            list.add("§o" + contents.stackSize + "x " + contents.getDisplayName());
        } else {
            list.add("§o" + Strings.TEMPLATE_EMPTY);
        }
        super.addInformation(is, player, list, b);
    }

    public static ItemStack create() {
        ItemStack is = new ItemStack(ModuleMechanical.template);
        is.stackTagCompound = new NBTTagCompound();
        is.stackTagCompound.setBoolean("hasContents", false);
        return is;
    }

    public static boolean hasContents(ItemStack template) {
        return template.stackTagCompound != null && template.stackTagCompound.getBoolean("hasContents") &&
                template.stackTagCompound.getCompoundTag("contents") != null && ItemStack.loadItemStackFromNBT(template.stackTagCompound.getCompoundTag("contents")) != null;
    }

    public static boolean setContents(ItemStack template, ItemStack contents) {
        if (template.stackTagCompound == null || template.stackTagCompound.getBoolean("hasContents") || contents == null) {
            return false;
        }
        NBTTagCompound contentsNBT = new NBTTagCompound();
        contents.writeToNBT(contentsNBT);
        template.stackTagCompound.setTag("contents", contentsNBT);
        template.stackTagCompound.setBoolean("hasContents", true);
        return true;
    }

    public static ItemStack getContents(ItemStack template, boolean remove) {
        if (template.stackTagCompound == null || !template.stackTagCompound.getBoolean("hasContents")) {
            return null;
        }
        ItemStack contents = ItemStack.loadItemStackFromNBT(template.stackTagCompound.getCompoundTag("contents"));
        if (remove) {
            template.stackTagCompound.setBoolean("hasContents", false);
            template.stackTagCompound.setTag("contents", new NBTTagCompound());
        }
        return contents;
    }
}
