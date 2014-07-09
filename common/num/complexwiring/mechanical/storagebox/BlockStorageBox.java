package num.complexwiring.mechanical.storagebox;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import num.complexwiring.base.ItemScrewdriver;
import num.complexwiring.client.RenderingHandler;
import num.complexwiring.lib.Reference;
import num.complexwiring.mechanical.ModuleMechanical;

import java.util.List;

public class BlockStorageBox extends Block implements ITileEntityProvider {
    public BlockStorageBox() {
        super(Material.wood);
        setBlockName(Reference.MOD_ID.toLowerCase() + ".mechanical.storagebox");
        setHardness(1.0F);
        setResistance(1.0F);
        setCreativeTab(ModuleMechanical.tabCWMechanical);
        isBlockContainer = true;
    }

    @Override
    public boolean hasTileEntity(int meta) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, int meta) {
        switch (meta) {
            case 0:
                return new TileStorageBox.TileStorageBoxBasic();
            case 1:
                return new TileStorageBox.TileStorageBoxAdvanced();
        }
        return null;
    }

    @Override
    public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest) {
        if(player.capabilities.isCreativeMode) {
            ItemStack itemStack = ((TileStorageBox) player.worldObj.getTileEntity(x, y, z)).removeAll();
            if (itemStack != null) {
                EntityItem entityItem = new EntityItem(world, x, y, z, itemStack);
                entityItem.setVelocity(0, 0, 0);
                entityItem.delayBeforeCanPickup = 0;
                player.worldObj.spawnEntityInWorld(entityItem);

                return false;
            }
        }
        return super.removedByPlayer(world, player, x, y, z, willHarvest);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (!world.isRemote && world.getTileEntity(x, y, z) instanceof TileStorageBox) {
            TileStorageBox tile = (TileStorageBox) world.getTileEntity(x, y, z);
            tile.onRightClick(player);
        }
        return true;
    }

    @Override
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
        if (!world.isRemote && world.getTileEntity(x, y, z) instanceof TileStorageBox) {
            TileStorageBox tile = (TileStorageBox) world.getTileEntity(x, y, z);
            tile.onLeftClick(player);
        }
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int par6) {
        ItemStack itemStack = ((TileStorageBox) world.getTileEntity(x, y, z)).getContaining();
        if (itemStack != null) {
            EntityItem entityItem = new EntityItem(world, x, y, z, itemStack);
            entityItem.setVelocity(0, 0, 0);
            entityItem.delayBeforeCanPickup = 0;
            world.spawnEntityInWorld(entityItem);
        }
        world.removeTileEntity(x, y, z);
        super.breakBlock(world, x, y, z, block, par6);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack is) {
        super.onBlockPlacedBy(world, x, y, z, entity, is);
        TileStorageBox tile = (TileStorageBox) world.getTileEntity(x, y, z);
        tile.setFacing(ItemScrewdriver.getSideToRotate(entity.rotationYaw, 0F, true));
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
        return EnumStorageBox.VALID[meta].front;
    }

    @Override
    public void registerBlockIcons(IIconRegister ir) {
        for (EnumStorageBox storageBox : EnumStorageBox.VALID) {
            storageBox.registerIcon(ir);
        }
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (EnumStorageBox storageBox : EnumStorageBox.VALID) {
            list.add(storageBox.getIS(1));
        }
    }

    public void registerTiles() {
        for (EnumStorageBox storageBox : EnumStorageBox.VALID) {
            storageBox.registerTile();
        }
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
