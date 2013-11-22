package num.complexwiring.world;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import num.complexwiring.lib.ModuleBase;

public class ModuleWorld extends ModuleBase {
    public static Block orePrimary;
    public static Block oreSecondary;

    @Override
    public void preInit() {
        registerBlocks();
        registerItems();
    }

    @Override
    public void init() {
        registerRecipes();
    }

    private void registerBlocks() {
        //TODO: CONFIGS!
        orePrimary = new BlockOrePrimary(666);
        GameRegistry.registerBlock(orePrimary, ItemBlockOrePrimary.class, orePrimary.getUnlocalizedName());

        oreSecondary = new BlockOreSecondary(667);
        GameRegistry.registerBlock(oreSecondary, ItemBlockOreSecondary.class, oreSecondary.getUnlocalizedName());
    }

    private void registerItems() {

    }

    private void registerRecipes() {

    }
}
