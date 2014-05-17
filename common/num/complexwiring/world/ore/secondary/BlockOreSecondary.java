package num.complexwiring.world.ore.secondary;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import num.complexwiring.lib.Reference;
import num.complexwiring.world.ModuleWorld;
import num.complexwiring.world.ore.secondary.classic.EnumOreSecondaryClassic;

import java.util.List;

public abstract class BlockOreSecondary extends Block {
    public BlockOreSecondary() {
        super(Material.rock);
        setBlockName(Reference.MOD_ID.toLowerCase() + ".world.ore.secondary");
        setHardness(3.0F);
        setResistance(3.0F);
        setCreativeTab(ModuleWorld.tabCWWorld);
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return EnumOreSecondaryClassic.VALID[meta].icon;
    }

    @Override
    public void registerBlockIcons(IIconRegister ir) {
        for (EnumOreSecondaryClassic oreSecondary : EnumOreSecondaryClassic.VALID) {
            oreSecondary.registerIcon(ir);
        }
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (EnumOreSecondaryClassic oreSecondary : EnumOreSecondaryClassic.VALID) {
            list.add(oreSecondary.getIS(1));
        }
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }

    public void registerOres() {
        for (EnumOreSecondaryClassic oreSecondary : EnumOreSecondaryClassic.VALID) {
            oreSecondary.registerOre();
        }
    }

}