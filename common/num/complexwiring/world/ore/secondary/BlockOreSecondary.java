package num.complexwiring.world.ore.secondary;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;
import num.complexwiring.lib.Reference;
import num.complexwiring.world.ModuleWorld;
import num.complexwiring.world.ore.secondary.classic.EnumOreSecondaryClassic;

import java.util.List;

public abstract class BlockOreSecondary extends Block {

    public BlockOreSecondary(int ID) {
        super(ID, Material.rock);
        setUnlocalizedName(Reference.MOD_ID.toLowerCase() + ".world.ore.secondary");
        setHardness(3.0F);
        setResistance(3.0F);
        setCreativeTab(ModuleWorld.tabCWWorld);
    }

    @Override
    public Icon getIcon(int side, int meta) {
        return EnumOreSecondaryClassic.VALID[meta].icon;
    }

    @Override
    public void registerIcons(IconRegister ir) {
        for (EnumOreSecondaryClassic oreSecondary : EnumOreSecondaryClassic.VALID) {
            oreSecondary.registerIcon(ir);
        }
    }

    @Override
    public void getSubBlocks(int ID, CreativeTabs tab, List list) {
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