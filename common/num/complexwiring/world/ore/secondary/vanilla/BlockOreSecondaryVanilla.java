package num.complexwiring.world.ore.secondary.vanilla;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;
import num.complexwiring.lib.Reference;
import num.complexwiring.world.ore.secondary.BlockOreSecondary;

import java.util.List;

public class BlockOreSecondaryVanilla extends BlockOreSecondary {
    public BlockOreSecondaryVanilla(int ID) {
        super(ID);
        setUnlocalizedName(Reference.MOD_ID.toLowerCase() + ".world.ore.secondary.vanilla");
    }

    @Override
    public Icon getIcon(int side, int meta) {
        return EnumOreSecondaryVanilla.VALID[meta].icon;
    }

    @Override
    public void registerIcons(IconRegister ir) {
        for (EnumOreSecondaryVanilla oreSecondary : EnumOreSecondaryVanilla.VALID) {
            oreSecondary.registerIcon(ir);
        }
    }

    @Override
    public void getSubBlocks(int ID, CreativeTabs tab, List list) {
        for (EnumOreSecondaryVanilla oreSecondary : EnumOreSecondaryVanilla.VALID) {
            list.add(oreSecondary.getIS(1));
        }
    }

    public void registerOres() {
        for (EnumOreSecondaryVanilla oreSecondary : EnumOreSecondaryVanilla.VALID) {
            oreSecondary.registerOre();
        }
    }

}