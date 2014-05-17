package num.complexwiring.world.ore.primary;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import num.complexwiring.lib.Reference;
import num.complexwiring.world.ModuleWorld;

import java.util.List;

public class BlockOrePrimary extends Block {
    public BlockOrePrimary() {
        super(Material.rock);
        setBlockName(Reference.MOD_ID.toLowerCase() + ".world.ore.primary");
        setHardness(3.0F);
        setResistance(3.0F);
        setCreativeTab(ModuleWorld.tabCWWorld);
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return EnumOrePrimary.VALID[meta].icon;
    }

    @Override
    public void registerBlockIcons(IIconRegister ir) {
        for (EnumOrePrimary orePrimary : EnumOrePrimary.VALID) {
            orePrimary.registerIcon(ir);
        }
    }

    @Override
    public void getSubBlocks(Item Item, CreativeTabs tab, List list) {
        for (EnumOrePrimary orePrimary : EnumOrePrimary.VALID) {
            list.add(orePrimary.getIS(1));
        }
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }

    public void registerOres() {
        for (EnumOrePrimary orePrimary : EnumOrePrimary.VALID) {
            orePrimary.registerOre();
        }
    }
}
