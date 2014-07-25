package num.complexwiring.world.ore.secondary.vanilla;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import num.complexwiring.core.Reference;
import num.complexwiring.world.ore.secondary.BlockOreSecondary;

import java.util.List;

public class BlockOreSecondaryVanilla extends BlockOreSecondary {
    public BlockOreSecondaryVanilla() {
        super();
        setBlockName(Reference.MOD_ID.toLowerCase() + ".world.ore.secondary.vanilla");
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return EnumOreSecondaryVanilla.VALID[meta].icon;
    }

    @Override
    public void registerBlockIcons(IIconRegister ir) {
        for (EnumOreSecondaryVanilla oreSecondary : EnumOreSecondaryVanilla.VALID) {
            oreSecondary.registerIcon(ir);
        }
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
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