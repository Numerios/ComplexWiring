package num.complexwiring.world;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import num.complexwiring.lib.Module;
import num.complexwiring.recipe.Recipe;
import num.complexwiring.recipe.RecipeManager;
import num.complexwiring.recipe.RecipeOutput;

public class ModuleWorld extends Module {
    public static Block orePrimary, oreSecondary;

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
        ((BlockOrePrimary) orePrimary).registerOres();
        GameRegistry.registerBlock(orePrimary, ItemBlockOrePrimary.class, orePrimary.getUnlocalizedName());

        oreSecondary = new BlockOreSecondary(667);
        ((BlockOreSecondary) oreSecondary).registerOres();
        GameRegistry.registerBlock(oreSecondary, ItemBlockOreSecondary.class, oreSecondary.getUnlocalizedName());
    }

    private void registerItems() {

    }

    private void registerRecipes() {
        RecipeManager.add(new Recipe(new ItemStack(Block.oreCoal),
                new RecipeOutput(EnumOreSecondary.ANTHRACITE.getIS(1), 0.1F),
                new RecipeOutput(EnumOreSecondary.BITUMINOUS.getIS(1), 0.45F),
                new RecipeOutput(EnumOreSecondary.SUBBITUMINOUS.getIS(1), 0.75F),
                new RecipeOutput(EnumOreSecondary.LIGNITE.getIS(1), 0.15F),
                new RecipeOutput(new ItemStack(Item.coal, 1), 0.2F)));
    }
}
