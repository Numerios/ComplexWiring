package num.complexwiring.mechanical.template;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import num.complexwiring.base.ItemScrewdriver;
import num.complexwiring.client.RenderingHandler;
import num.complexwiring.core.Reference;
import num.complexwiring.mechanical.ModuleMechanical;

import java.util.List;

public class BlockTemplater extends Block implements ITileEntityProvider {
    public IIcon icon;

    public BlockTemplater() {
        super(Material.rock);
        setBlockName(Reference.MOD_ID.toLowerCase() + ".mechanical.templater");
        setHardness(2F);
        setResistance(2F);
        setCreativeTab(ModuleMechanical.tabCWMechanical);
    }

    @Override
    public boolean hasTileEntity(int meta) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, int meta) {
        return new TileTemplater();
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int par6) {
        super.breakBlock(world, x, y, z, block, par6);
        world.removeTileEntity(x, y, z);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack is) {
        super.onBlockPlacedBy(world, x, y, z, entity, is);
        TileTemplater tile = (TileTemplater) world.getTileEntity(x, y, z);
        tile.setFacing(ItemScrewdriver.getSideToRotate(entity.rotationYaw, entity.rotationPitch, true));
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (!world.isRemote && world.getTileEntity(x, y, z) instanceof TileTemplater) {
            TileTemplater tile = (TileTemplater) world.getTileEntity(x, y, z);
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
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        list.add(new ItemStack(ModuleMechanical.templater));
    }

    public void registerTiles() {
        GameRegistry.registerTileEntity(TileTemplater.class, "tile.templater");
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
}
