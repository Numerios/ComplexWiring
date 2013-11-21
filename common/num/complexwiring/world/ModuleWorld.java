package num.complexwiring.world;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import num.complexwiring.lib.ModuleBase;

public class ModuleWorld extends ModuleBase {
    public static Block primaryOre;

    //HIGHLY TEMPORARY CODE COMING IN!
    static{
        LanguageRegistry.instance().addStringLocalization("complexwiring.world.primaryore", "en_US", "Primary Ore");
        LanguageRegistry.instance().addStringLocalization("complexwiring.world.primaryore.orecopper", "en_US", "Copper Ore");
        LanguageRegistry.instance().addStringLocalization("complexwiring.world.primaryore.oretin", "en_US", "Tin Ore");
        LanguageRegistry.instance().addStringLocalization("complexwiring.world.primaryore.oresilver", "en_US", "Silver Ore");
    }

    public void init() {
        //TODO: CONFIGS!
        primaryOre = new BlockPrimaryOre(1425);
        GameRegistry.registerBlock(primaryOre, ItemBlockPrimaryOre.class, primaryOre.getUnlocalizedName());


    }
}
