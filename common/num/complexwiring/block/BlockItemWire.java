package num.complexwiring.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import num.complexwiring.ComplexWiring;
import num.complexwiring.api.IItemConnectable;
import num.complexwiring.api.IItemWire;
import num.complexwiring.api.vec.Vector3;
import num.complexwiring.core.Wrapper;
import num.complexwiring.lib.Reference;
import num.complexwiring.util.WireHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockItemWire extends Block implements ITileEntityProvider {
    private Random random = new Random();

    public BlockItemWire(int id) {
        super(id, Material.circuits);
        setCreativeTab(ComplexWiring.tabCW);
        setUnlocalizedName("wireItem");
        setBlockBounds(0.3F, 0.3F, 0.3F, 0.7F, 0.7F, 0.7F);
    }

    @Override
    public boolean hasTileEntity(int meta) {
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileItemWire();
    }

    @Override
    public String getItemIconName() {
        return "BlockItemWire";
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister ir) {
        ir.registerIcon(Reference.TEXTURE_PATH + "blockWire");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta) {
        return blockIcon;
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        TileEntity tile = world.getBlockTileEntity(x, y, z);
        if (tile != null && tile instanceof IItemConnectable) {
            setBB(getAABB((IItemConnectable) tile));
        } else {
            super.setBlockBoundsBasedOnState(world, x, y, z);
        }
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        TileEntity tile = world.getBlockTileEntity(x, y, z);
        if (tile != null && tile instanceof IItemWire) {
            return getAABB((IItemConnectable) tile).offset(x, y, z);
        } else {
            return super.getCollisionBoundingBoxFromPool(world, x, y, z);
        }
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
        return getCollisionBoundingBoxFromPool(world, x, y, z);
    }

    private AxisAlignedBB getAABB(IItemConnectable tile) {
        AxisAlignedBB aabb = AxisAlignedBB.getAABBPool().getAABB(0.2F, 0.2F, 0.2F, 0.8F, 0.8F, 0.8F);
        for (ForgeDirection side : ForgeDirection.VALID_DIRECTIONS) {
            if (WireHelper.getConnection(side, tile, tile.getWorld())) {
                switch (side) {
                    case EAST: {
                        aabb.setBounds(aabb.minX, aabb.minY, aabb.minZ, 1.0F, aabb.maxY, aabb.maxZ);
                        break;
                    }
                    case WEST: {
                        aabb.setBounds(0.0F, aabb.minY, aabb.minZ, aabb.maxX, aabb.maxY, aabb.maxZ);
                        break;
                    }
                    case UP: {
                        aabb.setBounds(aabb.minX, aabb.minY, aabb.minZ, aabb.maxX, 1.0F, aabb.maxZ);
                        break;
                    }
                    case DOWN: {
                        aabb.setBounds(aabb.minX, 0.0F, aabb.minZ, aabb.maxX, aabb.maxY, aabb.maxZ);
                        break;
                    }
                    case SOUTH: {
                        aabb.setBounds(aabb.minX, aabb.minY, aabb.minZ, aabb.maxX, aabb.maxY, 1.0F);
                        break;
                    }
                    case NORTH: {
                        aabb.setBounds(aabb.minX, aabb.minY, 0.0F, aabb.maxX, aabb.maxY, aabb.maxZ);
                        break;
                    }
                    default:
                        break;
                }
            }
        }
        return aabb;
    }

    private void setBB(AxisAlignedBB aabb) {
        this.minX = aabb.minX;
        this.minY = aabb.minY;
        this.minZ = aabb.minZ;
        this.maxX = aabb.maxX;
        this.maxY = aabb.maxY;
        this.maxZ = aabb.maxZ;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int ID, int meta) {
        dropWrappers(world, x, y, z);
        super.breakBlock(world, x, y, z, ID, meta);
    }

    private void dropWrappers(World world, int x, int y, int z) {
        TileEntity wire = world.getBlockTileEntity(x, y, z);
        if (wire instanceof TileItemWire) {
            for (Wrapper wrapper : ((TileItemWire) wire).contents) {
                ItemStack is = wrapper.getContents();
                if (is == null) {
                    return;
                }
                double offsetX = 0.50D + random.nextDouble();
                double offsetY = 0.50D + random.nextDouble();
                double offsetZ = 0.50D + random.nextDouble();
                Vector3 vec3 = Vector3.getCenter(wire);
                EntityItem item = new EntityItem(wire.worldObj, vec3.x + offsetX, vec3.y + offsetY, vec3.z + offsetZ,
                        is);

                item.setVelocity(random.nextFloat() * 0.05F, random.nextFloat() * 0.10F + 0.10F,
                        random.nextFloat() * 0.05F);
                wire.worldObj.spawnEntityInWorld(item);
            }
        }
    }
}
