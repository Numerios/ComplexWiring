package num.complexwiring.base;

//import cpw.mods.fml.common.Optional;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.oredict.OreDictionary;
import num.complexwiring.core.BlockHelper;
import num.complexwiring.lib.Reference;

import java.util.List;

//@Optional.Interface(iface = "buildcraft.api.tools.IToolWrench", modid = "BuildCraft")
public class ItemScrewdriver extends Item /*implements IToolWrench*/ {
    public IIcon icon;

    public ItemScrewdriver() {
        super();
        setMaxDamage(256);
        setMaxStackSize(1);
        setUnlocalizedName(Reference.MOD_ID.toLowerCase() + ".base.screwdriver");
        setCreativeTab(ModuleBase.tabCWBase);
    }

    public static ForgeDirection getSideToRotate(float yaw, float pitch, boolean opposite) {
        ForgeDirection direction;
        if (yaw < 0) yaw += 360;
        int playerFacing = MathHelper.floor_double((yaw * 4F / 360F) + 0.5D);
        switch (playerFacing) {
            case 1:
                direction = ForgeDirection.WEST;
                break;
            case 2:
                direction = ForgeDirection.NORTH;
                break;
            case 3:
                direction = ForgeDirection.EAST;
                break;
            default:
                direction = ForgeDirection.SOUTH;
                break;
        }

        if (pitch < -60) direction = ForgeDirection.UP;
        if (pitch > 60) direction = ForgeDirection.DOWN;

        if (opposite) direction = direction.getOpposite();

        return direction;
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        Block block = world.getBlock(x, y, z);
        if (block != null) {
            int preMetadata = world.getBlockMetadata(x, y, z);
            int metadata = BlockHelper.rotateBlock(block, preMetadata);

            if(metadata != preMetadata) {
                world.setBlockMetadataWithNotify(x, y, z, metadata, 3);
                player.swingItem();
                stack.damageItem(1, player);
                return !world.isRemote;
            } else {
                block.rotateBlock(world, x, y, z, ForgeDirection.getOrientation(side));
            }
        }

        return false;
    }

    @Override
    public boolean doesSneakBypassUse(World world, int x, int y, int z, EntityPlayer player) {
        return true;
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
        OreDictionary.registerOre("CW:screwdriver", new ItemStack(ModuleBase.screwdriver));
    }

    /*@Optional.Method(modid = "BuildCraft")
    @Override
    public boolean canWrench(EntityPlayer player, int x, int y, int z) {
        return true;
    }

    @Optional.Method(modid = "BuildCraft")
    @Override
    public void wrenchUsed(EntityPlayer player, int x, int y, int z) {
        player.swingItem();
    }*/
}
