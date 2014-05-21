package num.complexwiring.machine.storagebox;

import codechicken.lib.math.MathHelper;
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
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import num.complexwiring.api.vec.Vector3;
import num.complexwiring.client.RenderingHandler;
import num.complexwiring.core.InventoryHelper;
import num.complexwiring.lib.Reference;
import num.complexwiring.machine.ModuleMachine;

import java.util.List;

public class BlockStorageBox extends Block implements ITileEntityProvider {
    public BlockStorageBox() {
        super(Material.wood);
        setBlockName(Reference.MOD_ID.toLowerCase() + ".machine.storagebox");
        setHardness(1.0F);
        setResistance(1.0F);
        setCreativeTab(ModuleMachine.tabCWMachine);
        isBlockContainer = true;
    }

    public static ItemStack add(TileStorageBox tile, ItemStack is) {
        if (is == null) {
            return is;
        }
        ItemStack containing = tile.getContaining();
        if (containing == null || containing.isItemEqual(is)) {
            int free = Math.max((tile.getSizeInventory() * 64) - (containing != null ? containing.stackSize : 0), 0);
            if (is.stackSize <= free) {
                tile.add(is);
                is = null;
            } else {
                tile.add(is, free);
                is.stackSize -= free;
            }
            return is;
        }
        if (is.stackSize <= 0) {
            return null;
        }
        return is;
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
        Vector3 vec3 = new Vector3(x, y, z);
        InventoryHelper.dropInventory(world, vec3);
        super.breakBlock(world, x, y, z, block, par6);
        world.removeTileEntity(x, y, z);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack is) {
        super.onBlockPlacedBy(world, x, y, z, entity, is);
        int facing = MathHelper.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        TileStorageBox tile = (TileStorageBox) world.getTileEntity(x, y, z);
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
