package num.complexwiring.base;

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
import num.complexwiring.lib.Reference;

import java.util.List;

public class ItemScrewdriver extends Item {
    public IIcon icon;

    public ItemScrewdriver() {
        super();
        setUnlocalizedName(Reference.MOD_ID.toLowerCase() + ".base.screwdriver");
        setCreativeTab(ModuleBase.tabCWBase);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        Block block = world.getBlock(x, y, z);
        if (block != null) {
            ForgeDirection direction;
            int playerFacing = MathHelper.floor_double((player.rotationYaw * 4F / 360F) + 0.5D) + 4;
            switch (playerFacing){
                case 1: direction = ForgeDirection.WEST; break;
                case 2: direction = ForgeDirection.NORTH; break;
                case 3: direction = ForgeDirection.EAST; break;
                default: direction = ForgeDirection.SOUTH; break;
            }

            if(player.rotationPitch < -60) direction = ForgeDirection.UP;
            if(player.rotationPitch > 60) direction = ForgeDirection.DOWN;

            if(!player.isSneaking()) direction = direction.getOpposite();

            if (block.rotateBlock(world, x, y, z, direction)) {
                player.swingItem();
                return !world.isRemote;
            }
        }

        return false;
    }

    public static ForgeDirection getSideToRotate(float yaw, float pitch, boolean opposite){
        ForgeDirection direction;
        int playerFacing = MathHelper.floor_double((yaw * 4F / 360F) + 0.5D) + 4;
        switch (playerFacing){
            case 1: direction = ForgeDirection.WEST; break;
            case 2: direction = ForgeDirection.NORTH; break;
            case 3: direction = ForgeDirection.EAST; break;
            default: direction = ForgeDirection.SOUTH; break;
        }

        if(pitch < -60) direction = ForgeDirection.UP;
        if(pitch > 60) direction = ForgeDirection.DOWN;

        if(opposite) direction = direction.getOpposite();

        return direction;
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
}
