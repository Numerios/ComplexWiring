package num.complexwiring.tools;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import num.complexwiring.api.vec.Vector3;
import num.complexwiring.lib.Reference;
import num.complexwiring.machine.ModuleMachine;

import java.util.List;
import java.util.Random;

public class ItemToolSaw extends ItemAxe {
    private Random rand;

    public ItemToolSaw(int ID) {
        super(ID, ModuleTools.toolMaterial);
        rand = new Random();
        setUnlocalizedName(Reference.MOD_ID.toLowerCase() + ".tools.saw");
        setCreativeTab(ModuleMachine.tabCWMachine);
        setNoRepair();
        setMaxStackSize(1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister ir) {
        itemIcon = ir.registerIcon(Reference.TEXTURE_PATH + "saw");
    }

    @Override
    public boolean hasContainerItem() {
        return true;
    }

    @Override
    public ItemStack getContainerItemStack(ItemStack is) {
        if (is.itemID == this.itemID) {
            is.setItemDamage(is.getItemDamage() + 1);
            return is;
        } else {
            ItemStack returned = new ItemStack(this);
            returned.setItemDamage(returned.getMaxDamage());
            return returned;
        }
    }

    @Override
    public boolean onBlockDestroyed(ItemStack is1, World world, int ID, int x, int y, int z, EntityLivingBase entity) {
        Block b = Block.blocksList[ID];
        Vector3 vec3 = new Vector3(x, y, z);
        ItemStack is = new ItemStack(b, vec3.blockMetadata(world));
        int oreID = OreDictionary.getOreID(is);
        if (b != null && entity instanceof EntityPlayer) {
            if (OreDictionary.getOreName(oreID).contains("wood") || OreDictionary.getOreName(oreID).contains("log") || b == Block.wood){
                ItemStack result = new ItemStack(Block.planks, 6);
                if (!world.isRemote && world.getGameRules().getGameRuleBooleanValue("doTileDrops")) {  // taken from Block.class
                    float f = 0.7F;
                    double d0 = (double) (world.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
                    double d1 = (double) (world.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
                    double d2 = (double) (world.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
                    EntityItem entityitem = new EntityItem(world, (double) x + d0, (double) y + d1, (double) z + d2, result);
                    entityitem.delayBeforeCanPickup = 10;
                    world.spawnEntityInWorld(entityitem);
                }
                world.setBlock(x, y, z, 0, 0, 3);
            }
        }
        return super.onBlockDestroyed(is, world, ID, x, y, z, entity);
    }

    @Override
    public boolean doesContainerItemLeaveCraftingGrid(ItemStack is) {
        return false;
    }

    @Override
    public void getSubItems(int id, CreativeTabs tab, List list) {
        list.add(new ItemStack(this));
    }
}
