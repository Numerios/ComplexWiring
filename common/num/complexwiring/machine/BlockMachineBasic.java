package num.complexwiring.machine;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import num.complexwiring.ComplexWiring;
import num.complexwiring.core.GuiHandler;
import num.complexwiring.core.Logger;
import num.complexwiring.lib.Reference;

public class BlockMachineBasic extends Block implements ITileEntityProvider {

    public BlockMachineBasic(int ID) {
        super(ID, Material.rock);
        setUnlocalizedName(Reference.MOD_ID.toLowerCase() + ".machine.basic");
        setHardness(3.0F);
        setResistance(3.0F);
        setCreativeTab(ComplexWiring.tabCW);
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
        if (!player.isSneaking()) {
            Logger.debug("OPENING GUI");
            player.openGui(ComplexWiring.instance, GuiHandler.GUI_MACHINE_BASIC, world, x, y, z);
            return true;
        }
        return false;
    }
}
