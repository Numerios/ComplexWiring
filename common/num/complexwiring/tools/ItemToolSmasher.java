package num.complexwiring.tools;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import num.complexwiring.api.vec.Vector3;
import num.complexwiring.base.EnumDust;
import num.complexwiring.lib.Reference;

import java.util.List;
import java.util.Random;

public class ItemToolSmasher extends ItemPickaxe {
    private Random rand;

    public ItemToolSmasher() {
        super(ModuleTools.toolMaterial);
        rand = new Random();
        setUnlocalizedName(Reference.MOD_ID.toLowerCase() + ".tools.smasher");
        setCreativeTab(ModuleTools.tabCWTools);
        setNoRepair();
        setMaxStackSize(1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir) {
        itemIcon = ir.registerIcon(Reference.TEXTURE_PATH + "smasher");
    }

    /*@Override
    public boolean hasContainerItem() {
        return true;
    }

    @Override
    public ItemStack getContainerItemStack(ItemStack is) {                TODO: FIX CONTAINER ITEMS /1.7/
        if (is.itemID == this.itemID) {
            is.setItemDamage(is.getItemDamage() + 1);
            return is;
        } else {
            ItemStack returned = new ItemStack(this);
            returned.setItemDamage(returned.getMaxDamage());
            return returned;
        }
    }            */

    @Override
    public boolean onBlockDestroyed(ItemStack is1, World world, Block block, int x, int y, int z, EntityLivingBase entity) {
        ItemStack result = null;
        Vector3 vec3 = new Vector3(x, y, z);
        ItemStack is = new ItemStack(block, 1, vec3.blockMetadata(world));
        String oreName = OreDictionary.getOreName(OreDictionary.getOreID(is));
        if (block != null && entity instanceof EntityPlayer) {
            if (oreName.contains("ore")) {
                if (oreName.contains("oreIron")) {
                    result = EnumDust.IRON.getIS(2);
                } else if (oreName.contains("oreGold")) {
                    result = EnumDust.GOLD.getIS(2);
                } else if (oreName.contains("oreCopper")) {
                    result = EnumDust.COPPER.getIS(2);
                } else if (oreName.contains("oreTin")) {
                    result = EnumDust.TIN.getIS(2);
                } else if (oreName.contains("oreSilver")) {
                    result = EnumDust.SILVER.getIS(2);
                } else if (oreName.contains("oreLead")) {
                    result = EnumDust.LEAD.getIS(2);
                }

                if (!world.isRemote && world.getGameRules().getGameRuleBooleanValue("doTileDrops") && result != null) {  // taken from Block.class
                    ItemStack dropped = result.copy();
                    float f = 0.7F;
                    double d0 = (double) (world.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
                    double d1 = (double) (world.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
                    double d2 = (double) (world.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
                    EntityItem entityitem = new EntityItem(world, (double) x + d0, (double) y + d1, (double) z + d2, dropped);
                    entityitem.delayBeforeCanPickup = 10;
                    world.spawnEntityInWorld(entityitem);
                }
                world.setBlock(x, y, z, Blocks.air, 0, 3);
            }
        }
        return super.onBlockDestroyed(is, world, block, x, y, z, entity);
    }

    @Override
    public boolean doesContainerItemLeaveCraftingGrid(ItemStack is) {
        return false;
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        list.add(new ItemStack(this));
    }
}
