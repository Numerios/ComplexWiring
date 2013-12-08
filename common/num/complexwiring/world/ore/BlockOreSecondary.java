package num.complexwiring.world.ore;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;
import num.complexwiring.ComplexWiring;
import num.complexwiring.lib.Reference;

import java.util.List;

public class BlockOreSecondary extends Block {
    public BlockOreSecondary(int ID) {
        super(ID, Material.rock);
        setUnlocalizedName(Reference.MOD_ID.toLowerCase() + ".world.ore.secondary");
        setHardness(3.0F);
        setResistance(3.0F);
        setCreativeTab(ComplexWiring.tabCW);
    }

    @Override
    public Icon getIcon(int side, int meta) {
        return EnumOreSecondary.VALID[meta].icon;
    }

    @Override
    public void registerIcons(IconRegister ir) {
        for (EnumOreSecondary oreSecondary : EnumOreSecondary.VALID) {
            oreSecondary.registerIcon(ir);
        }
    }

    @Override
    public void getSubBlocks(int ID, CreativeTabs tab, List list) {
        for (EnumOreSecondary oreSecondary : EnumOreSecondary.VALID) {
            list.add(oreSecondary.getIS(1));
        }
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }

    public void registerOres() {
        for (EnumOreSecondary oreSecondary : EnumOreSecondary.VALID) {
            oreSecondary.registerOre();
        }
    }
}
