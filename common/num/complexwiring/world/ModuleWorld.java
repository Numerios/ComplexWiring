package num.complexwiring.world;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import num.complexwiring.lib.ModuleBase;

public class ModuleWorld extends ModuleBase {
    public static Block primaryOre;
    public static Block secondaryOre;

    @Override
    public void init() {
        //TODO: CONFIGS!
        primaryOre = new BlockPrimaryOre(666);
        GameRegistry.registerBlock(primaryOre, ItemBlockPrimaryOre.class, primaryOre.getUnlocalizedName());

        secondaryOre = new BlockSecondaryOre(667);
        GameRegistry.registerBlock(secondaryOre, ItemBlockSecondaryOre.class, secondaryOre.getUnlocalizedName());
    }
}
