package num.complexwiring.world.ore.secondary.classic;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import num.complexwiring.core.Reference;
import num.complexwiring.world.ore.secondary.BlockOreSecondary;

import java.util.List;

public class BlockOreSecondaryClassic extends BlockOreSecondary {
    public BlockOreSecondaryClassic() {
        super();
        setBlockName(Reference.MOD_ID.toLowerCase() + ".world.ore.secondary.classic");
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

    public void registerOres() {
        for (EnumOreSecondaryClassic oreSecondary : EnumOreSecondaryClassic.VALID) {
            oreSecondary.registerOre();
        }
    }

}