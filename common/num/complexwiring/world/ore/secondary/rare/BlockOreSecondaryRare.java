package num.complexwiring.world.ore.secondary.rare;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import num.complexwiring.lib.Reference;
import num.complexwiring.world.ore.secondary.BlockOreSecondary;

import java.util.List;

public class BlockOreSecondaryRare extends BlockOreSecondary {
    public BlockOreSecondaryRare() {
        super();
        setBlockName(Reference.MOD_ID.toLowerCase() + ".world.ore.secondary.rare");
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return EnumOreSecondaryRare.VALID[meta].icon;
    }

    @Override
    public void registerBlockIcons(IIconRegister ir) {
        for (EnumOreSecondaryRare oreSecondary : EnumOreSecondaryRare.VALID) {
            oreSecondary.registerIcon(ir);
        }
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
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