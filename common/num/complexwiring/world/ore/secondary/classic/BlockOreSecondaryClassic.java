package num.complexwiring.world.ore.secondary.classic;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;
import num.complexwiring.lib.Reference;
import num.complexwiring.world.ore.secondary.BlockOreSecondary;

import java.util.List;

public class BlockOreSecondaryClassic extends BlockOreSecondary {
    public BlockOreSecondaryClassic(int ID) {
        super(ID);
        setUnlocalizedName(Reference.MOD_ID.toLowerCase() + ".world.ore.secondary.classic");
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

    public void registerOres() {
        for (EnumOreSecondaryClassic oreSecondary : EnumOreSecondaryClassic.VALID) {
            oreSecondary.registerOre();
        }
    }

}