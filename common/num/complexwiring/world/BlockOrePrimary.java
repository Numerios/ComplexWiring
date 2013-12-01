package num.complexwiring.world;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;
import num.complexwiring.ComplexWiring;
import num.complexwiring.lib.Reference;

import java.util.List;

public class BlockOrePrimary extends Block {
    public BlockOrePrimary(int ID) {
        super(ID, Material.rock);
        setUnlocalizedName(Reference.MOD_ID.toLowerCase() + ".world.ore.primary");
        setHardness(3.0F);
        setResistance(3.0F);
        setCreativeTab(ComplexWiring.tabCW);
    }

    @Override
    public Icon getIcon(int side, int meta) {
        return EnumOrePrimary.VALID[meta].icon;
    }

    @Override
    public void registerIcons(IconRegister ir) {
        for (EnumOrePrimary orePrimary : EnumOrePrimary.VALID) {
            orePrimary.registerIcon(ir);
        }
    }

    @Override
    public void getSubBlocks(int ID, CreativeTabs tab, List list) {
        for (EnumOrePrimary orePrimary : EnumOrePrimary.VALID) {
            list.add(orePrimary.getIS(1));
        }
    }

    public void registerOre() {
        for (EnumOrePrimary orePrimary : EnumOrePrimary.VALID) {
            orePrimary.registerOre();
        }
    }

}
