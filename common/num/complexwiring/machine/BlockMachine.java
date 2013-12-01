package num.complexwiring.machine;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import num.complexwiring.ComplexWiring;
import num.complexwiring.api.vec.Vector3;
import num.complexwiring.core.InventoryHelper;
import num.complexwiring.lib.Reference;

import java.util.List;

public class BlockMachine extends Block implements ITileEntityProvider {

    public BlockMachine(int ID) {
        super(ID, Material.iron);
        setUnlocalizedName(Reference.MOD_ID.toLowerCase() + ".machine");
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
    public TileEntity createTileEntity(World world, int meta) {
        switch (meta) {
            case 0: {    //TODO: Make it a little more flexible
                return new TileEntityMachineOrealyzer();
            }
        }
        return null;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        Vector3 vec3 = new Vector3(x, y, z);
        int meta = vec3.blockMetadata(world);
        if (!world.isRemote)
            player.openGui(ComplexWiring.instance, meta, world, x, y, z);
        return true;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
        Vector3 vec3 = new Vector3(x, y, z);
        InventoryHelper.dropInventory(world, vec3);
        super.breakBlock(world, x, y, z, par5, par6);
        world.removeBlockTileEntity(x, y, z);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return null;
    }

    @Override
    public Icon getIcon(int side, int meta) {
        return EnumMachine.VALID[meta].icon;
    }

    @Override
    public void registerIcons(IconRegister ir) {
        for (EnumMachine machine : EnumMachine.VALID) {
            machine.registerIcon(ir);
        }
    }

    @Override
    public void getSubBlocks(int ID, CreativeTabs tab, List list) {
        for (EnumMachine machine : EnumMachine.VALID) {
            list.add(machine.getIS(1));
        }
    }

    public void registerTiles() {
        for (EnumMachine machine : EnumMachine.VALID) {
            machine.registerTile();
        }
    }
}
