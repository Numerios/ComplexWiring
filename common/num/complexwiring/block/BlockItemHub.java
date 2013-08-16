package num.complexwiring.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import num.complexwiring.ComplexWiring;
import num.complexwiring.core.Wrapper;
import num.complexwiring.util.MCVector3;

public class BlockItemHub extends Block implements ITileEntityProvider {
    private Random random = new Random();
    public BlockItemHub(int ID) {
        super(ID, Material.iron);
        setCreativeTab(ComplexWiring.tabCW);
        setUnlocalizedName("hubItem");
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileItemHub();
    }
    
    @Override
    public void breakBlock(World world, int x, int y, int z, int ID, int meta){
        dropWrappers(world, x, y, z);
        super.breakBlock(world, x, y, z, ID, meta);
    }

    private void dropWrappers(World world, int x, int y, int z) {
        TileEntity wire = world.getBlockTileEntity(x, y, z);
        if (wire instanceof TileItemHub){
            for (Wrapper wrapper : ((TileItemHub) wire).contents){
                ItemStack is = wrapper.getContents();
                if(is == null){
                    return;
                }
                double offsetX = 0.50D + random.nextDouble();
                double offsetY = 0.50D + random.nextDouble();
                double offsetZ = 0.50D + random.nextDouble();
                MCVector3 vec3 = MCVector3.get(wire);
                EntityItem item = new EntityItem(wire.worldObj, vec3.x + offsetX, vec3.y + offsetY, vec3.z + offsetZ, is);
                
                item.setVelocity(random.nextFloat() * 0.05F, random.nextFloat() * 0.10F + 0.10F, random.nextFloat() * 0.05F);
                wire.worldObj.spawnEntityInWorld(item);
            }
        }
    }
}
