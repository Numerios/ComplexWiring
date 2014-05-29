package num.complexwiring.machine.smasher;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import num.complexwiring.client.RenderingHandler;
import num.complexwiring.lib.Reference;
import num.complexwiring.machine.ModuleMachine;

import java.util.List;

public class BlockSmasher extends Block implements ITileEntityProvider {
    public IIcon icon;

    public BlockSmasher() {
        super(Material.rock);
        setBlockName(Reference.MOD_ID.toLowerCase() + ".machine.smasher");
        setHardness(2F);
        setResistance(2F);
        setCreativeTab(ModuleMachine.tabCWMachine);
    }

    @Override
    public boolean hasTileEntity(int meta) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, int meta) {
        return new TileSmasher();
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int par6) {
        super.breakBlock(world, x, y, z, block, par6);
        world.removeTileEntity(x, y, z);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack is) {
        super.onBlockPlacedBy(world, x, y, z, entity, is);
        int facing = MathHelper.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        TileSmasher tile = (TileSmasher) world.getTileEntity(x, y, z);
        if (tile != null) {
            switch (facing) {
                case 0:
                    tile.setFacing(ForgeDirection.NORTH);
                    break;
                case 1:
                    tile.setFacing(ForgeDirection.EAST);
                    break;
                case 2:
                    tile.setFacing(ForgeDirection.SOUTH);
                    break;
                case 3:
                    tile.setFacing(ForgeDirection.WEST);
                    break;
            }
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (!world.isRemote && world.getTileEntity(x, y, z) instanceof TileSmasher) {
            TileSmasher tile = (TileSmasher) world.getTileEntity(x, y, z);
            tile.onRightClick(player);
        }
        return true;
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return null;
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return icon;
    }

    @Override
    public void registerBlockIcons(IIconRegister ir) {
        icon = ir.registerIcon("smasher");
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        list.add(new ItemStack(ModuleMachine.smasher));
    }

    public void registerTiles() {
        GameRegistry.registerTileEntity(TileSmasher.class, "tile.smasher");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderType() {
        return RenderingHandler.renderID;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }
}
