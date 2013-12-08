package num.complexwiring.world.ore.secondary.classic;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;
import num.complexwiring.lib.Reference;
import num.complexwiring.world.ore.secondary.BlockOreSecondary;

import java.util.ArrayList;
import java.util.List;

public class BlockOreSecondaryClassic extends BlockOreSecondary {

    public static List<EnumOreSecondaryClassic> oreList;
    
    public BlockOreSecondaryClassic(int ID) {
        super(ID);
        setUnlocalizedName(Reference.MOD_ID.toLowerCase() + ".world.ore.secondary.classic");
        oreList = new ArrayList<EnumOreSecondaryClassic>();
        for (EnumOreSecondaryClassic oreSecondary : EnumOreSecondaryClassic.VALID){
            oreList.add(oreSecondary);
        }
    }

    @Override
    public Icon getIcon(int side, int meta) {
        return oreList.get(meta).icon;
    }

    @Override
    public void registerIcons(IconRegister ir) {
        for (EnumOreSecondaryClassic oreSecondary : oreList) {
            oreSecondary.registerIcon(ir);
        }
    }

    @Override
    public void getSubBlocks(int ID, CreativeTabs tab, List list) {
        for (EnumOreSecondaryClassic oreSecondary : oreList) {
            list.add(oreSecondary.getIS(1));
        }
    }

    public void registerOres() {
        for (EnumOreSecondaryClassic oreSecondary : oreList) {
            oreSecondary.registerOre();
        }
    }

}