package num.complexwiring.world.decor;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import num.complexwiring.lib.Reference;
import num.complexwiring.world.ModuleWorld;

import java.util.List;

public class BlockDecor extends Block {
    public BlockDecor() {
        super(Material.rock);
        setBlockName(Reference.MOD_ID.toLowerCase() + ".world.decor");
        setHardness(2.0F);
        setResistance(3.0F);
        setCreativeTab(ModuleWorld.tabCWWorld);
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return EnumDecor.VALID[meta].icon;
    }

    @Override
    public void registerBlockIcons(IIconRegister ir) {
        for (EnumDecor orePrimary : EnumDecor.VALID) {
            orePrimary.registerIcon(ir);
        }
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (EnumDecor orePrimary : EnumDecor.VALID) {
            list.add(orePrimary.getIS(1));
        }
    }

    @Override
    public int damageDropped(int meta) {
        if(meta == EnumDecor.ARENITE.meta) return EnumDecor.ARENITE_ROUGH.meta;
        if(meta == EnumDecor.DOLOMITE.meta) return EnumDecor.DOLOMITE_ROUGH.meta;
        if(meta == EnumDecor.LIMESTONE.meta) return EnumDecor.LIMESTONE_ROUGH.meta;

        return meta;
    }

    @Override
    public int getDamageValue(World world, int x, int y, int z) {
        return world.getBlockMetadata(x, y, z);
    }

    public void registerOres() {
        for (EnumDecor orePrimary : EnumDecor.VALID) {
            orePrimary.registerOre();
        }
    }
}
