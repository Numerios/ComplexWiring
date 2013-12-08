package num.complexwiring.world;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import num.complexwiring.api.recipe.RecipeRandomOutput;
import num.complexwiring.core.CreativeTabCW;
import num.complexwiring.lib.Module;
import num.complexwiring.recipe.OrelyzerRecipe;
import num.complexwiring.recipe.RecipeManager;
import num.complexwiring.world.ore.*;

public class ModuleWorld extends Module {

    public static Block orePrimary, oreSecondary;
    public static CreativeTabCW tabCWWorld = new CreativeTabCW("tabCWWorld", null);

    @Override
    public void preInit() {
        registerBlocks();
        registerItems();
        tabCWWorld.setIcon(new ItemStack(orePrimary, 1, 3));
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
        RecipeManager.add(new OrelyzerRecipe(new ItemStack(Block.oreCoal), 320,
                new RecipeRandomOutput(EnumOreSecondary.ANTHRACITE.getIS(1), 0.1F),
                new RecipeRandomOutput(EnumOreSecondary.BITUMINOUS.getIS(1), 0.45F),
                new RecipeRandomOutput(EnumOreSecondary.SUBBITUMINOUS.getIS(1), 0.75F),
                new RecipeRandomOutput(EnumOreSecondary.LIGNITE.getIS(1), 0.15F),
                new RecipeRandomOutput(new ItemStack(Item.coal, 1), 0.2F)));
    }
}
