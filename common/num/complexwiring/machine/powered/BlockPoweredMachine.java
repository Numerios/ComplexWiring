package num.complexwiring.machine.powered;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import num.complexwiring.ComplexWiring;
import num.complexwiring.api.vec.Vector3;
import num.complexwiring.core.InventoryHelper;
import num.complexwiring.lib.Reference;
import num.complexwiring.machine.ModuleMachine;

import java.util.List;

public class BlockPoweredMachine extends Block implements ITileEntityProvider {

    public BlockPoweredMachine() {
        super(Material.iron);
        setBlockName(Reference.MOD_ID.toLowerCase() + ".machine.powered");
        setHardness(3.0F);
        setResistance(3.0F);
        setCreativeTab(ModuleMachine.tabCWMachine);
        isBlockContainer = true;
    }

    @Override
    public boolean hasTileEntity(int meta) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, int meta) {
        switch (meta) {
            case 0:     //TODO: Make it a little more flexible
                return new TileEntityBCPoweredOrelyzer();
            case 1:
                return new TileEntityBCPoweredFurnace();
            case 2:
                return new TileEntityBCPoweredCrusher();
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
    public void breakBlock(World world, int x, int y, int z, Block block, int par6) {
        Vector3 vec3 = new Vector3(x, y, z);
        InventoryHelper.dropInventory(world, vec3);
        super.breakBlock(world, x, y, z, block, par6);
        world.removeTileEntity(x, y, z);
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
        return EnumPoweredMachine.VALID[meta].icon;
    }

    @Override
    public void registerBlockIcons(IIconRegister ir) {
        for (EnumPoweredMachine machine : EnumPoweredMachine.VALID) {
            machine.registerIcon(ir);
        }
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (EnumPoweredMachine machine : EnumPoweredMachine.VALID) {
            list.add(machine.getIS(1));
        }
    }

    public void registerTiles() {
        for (EnumPoweredMachine machine : EnumPoweredMachine.VALID) {
            machine.registerTile();
        }
    }
}
