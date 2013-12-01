package num.complexwiring.machine;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import num.complexwiring.ComplexWiring;
import num.complexwiring.api.vec.Vector3;
import num.complexwiring.core.GuiHandler;
import num.complexwiring.core.InventoryHelper;
import num.complexwiring.lib.Reference;

public class BlockMachineBasic extends Block implements ITileEntityProvider {

    public BlockMachineBasic(int ID) {
        super(ID, Material.rock);
        setUnlocalizedName(Reference.MOD_ID.toLowerCase() + ".machine.basic");
        setHardness(3.0F);
        setResistance(3.0F);
        setCreativeTab(ComplexWiring.tabCW);
        isBlockContainer = true;
    }

    @Override
    public boolean hasTileEntity(int meta) {
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityMachineBasic();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (!world.isRemote)
            player.openGui(ComplexWiring.instance, GuiHandler.GUI_MACHINE_BASIC, world, x, y, z);
        return true;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6){
        Vector3 vec3 = new Vector3(x, y, z);
        InventoryHelper.dropInventory(world, vec3);
        super.breakBlock(world, x, y, z, par5, par6);
        world.removeBlockTileEntity(x, y, z);
    }
}
