package num.complexwiring.world.ore.secondary.rare;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;
import num.complexwiring.lib.Reference;
import num.complexwiring.world.ore.secondary.BlockOreSecondary;

import java.util.List;

public class BlockOreSecondaryRare extends BlockOreSecondary {
    public BlockOreSecondaryRare(int ID) {
        super(ID);
        setUnlocalizedName(Reference.MOD_ID.toLowerCase() + ".world.ore.secondary.rare");
    }

    @Override
    public Icon getIcon(int side, int meta) {
        return EnumOreSecondaryRare.VALID[meta].icon;
    }

    @Override
    public void registerIcons(IconRegister ir) {
        for (EnumOreSecondaryRare oreSecondary : EnumOreSecondaryRare.VALID) {
            oreSecondary.registerIcon(ir);
        }
    }

    @Override
    public void getSubBlocks(int ID, CreativeTabs tab, List list) {
        for (EnumOreSecondaryRare oreSecondary : EnumOreSecondaryRare.VALID) {
            list.add(oreSecondary.getIS(1));
        }
    }

    public void registerOres() {
        for (EnumOreSecondaryRare oreSecondary : EnumOreSecondaryRare.VALID) {
            oreSecondary.registerOre();
        }
    }

}