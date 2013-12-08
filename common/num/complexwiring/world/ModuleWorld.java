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
        RecipeManager.add(new OrelyzerRecipe(EnumOrePrimary.COPPER.getIS(1), 240,
               new RecipeRandomOutput(EnumOreSecondary.CHALCOCITE.getIS(1), 0.65F),
               new RecipeRandomOutput(EnumOreSecondary.CHALCOPYRITE.getIS(1), 0.35F),
               new RecipeRandomOutput(EnumOreSecondary.BORNITE.getIS(1), 0.25F),
               new RecipeRandomOutput(EnumOreSecondary.MALACHITE.getIS(1), 0.10F)));

        RecipeManager.add(new OrelyzerRecipe(EnumOrePrimary.TIN.getIS(1), 240,
                new RecipeRandomOutput(EnumOreSecondary.CASSITERITE.getIS(1), 0.75F),
                new RecipeRandomOutput(EnumOreSecondary.STANNITE.getIS(1), 0.40F)));

        RecipeManager.add(new OrelyzerRecipe(EnumOrePrimary.SILVER.getIS(1), 360,
                new RecipeRandomOutput(EnumOreSecondary.ARGENTITE.getIS(1), 0.80F),
                new RecipeRandomOutput(EnumOreSecondary.CHLORARGYRITE.getIS(1), 0.35F)));

        RecipeManager.add(new OrelyzerRecipe(EnumOrePrimary.LEAD.getIS(1), 360,
                new RecipeRandomOutput(EnumOreSecondary.GALENA.getIS(1), 0.75F),
                new RecipeRandomOutput(EnumOreSecondary.CERUSSITE.getIS(1), 0.25F),
                new RecipeRandomOutput(EnumOreSecondary.ANGLESITE.getIS(1), 0.20F)));

        RecipeManager.add(new OrelyzerRecipe(EnumOrePrimary.ALUMINIUM.getIS(1), 480,
                new RecipeRandomOutput(EnumOreSecondary.BAUXITE.getIS(1), 0.75F),
                new RecipeRandomOutput(EnumOreSecondary.CRYOLITE.getIS(1), 0.375F)));

        RecipeManager.add(new OrelyzerRecipe(EnumOrePrimary.URANIUM.getIS(1), 640,
                new RecipeRandomOutput(EnumOreSecondary.URANINITE.getIS(1), 0.75F),
                new RecipeRandomOutput(EnumOreSecondary.AUTUNITE.getIS(1), 0.15F)));

        RecipeManager.add(new OrelyzerRecipe(EnumOrePrimary.TUNGSTEN.getIS(1), 720,
                new RecipeRandomOutput(EnumOreSecondary.WOLFRAMITE.getIS(1), 0.65F),
                new RecipeRandomOutput(EnumOreSecondary.SCHEELITE.getIS(1), 0.45F)));

        RecipeManager.add(new OrelyzerRecipe(EnumOrePrimary.TITANIUM.getIS(1), 720,
                new RecipeRandomOutput(EnumOreSecondary.ILMENITE.getIS(1), 0.60F),
                new RecipeRandomOutput(EnumOreSecondary.RUTILE.getIS(1), 0.50F)));

        RecipeManager.add(new OrelyzerRecipe(new ItemStack(Block.oreGold), 360,
                new RecipeRandomOutput(EnumOreSecondary.CALAVERITE.getIS(1), 0.35F),
                new RecipeRandomOutput(EnumOreSecondary.SYLVANITE.getIS(1), 0.35F),
                new RecipeRandomOutput(EnumOreSecondary.PUREGOLD.getIS(1), 0.55F)));

        RecipeManager.add(new OrelyzerRecipe(new ItemStack(Block.oreIron), 240,
                new RecipeRandomOutput(EnumOreSecondary.MAGNETITE.getIS(1), 0.50F),
                new RecipeRandomOutput(EnumOreSecondary.HEMATITE.getIS(1), 0.70F),
                new RecipeRandomOutput(EnumOreSecondary.SIDERITE.getIS(1), 0.40F),
                new RecipeRandomOutput(EnumOreSecondary.CHROMITE.getIS(1), 0.20F),
                new RecipeRandomOutput(EnumOreSecondary.PENTLADITE.getIS(1), 0.20F)));

        RecipeManager.add(new OrelyzerRecipe(new ItemStack(Block.oreCoal), 320,
                new RecipeRandomOutput(EnumOreSecondary.ANTHRACITE.getIS(1), 0.10F),
                new RecipeRandomOutput(EnumOreSecondary.BITUMINOUS.getIS(1), 0.50F),
                new RecipeRandomOutput(EnumOreSecondary.SUBBITUMINOUS.getIS(1), 0.75F),
                new RecipeRandomOutput(EnumOreSecondary.LIGNITE.getIS(1), 0.15F),
                new RecipeRandomOutput(new ItemStack(Item.coal, 1), 0.15F)));

        RecipeManager.add(new OrelyzerRecipe(new ItemStack(Block.stone), 640,
                new RecipeRandomOutput(EnumOreSecondary.PYROLUSITE.getIS(1), 0.05F),
                new RecipeRandomOutput(EnumOreSecondary.CINNABAR.getIS(1), 0.05F),
                new RecipeRandomOutput(EnumOreSecondary.DOLOMITE.getIS(1), 0.30F),
                new RecipeRandomOutput(EnumOreSecondary.SPHALERITE.getIS(1), 0.05F),
                new RecipeRandomOutput(EnumOreSecondary.LIMESTONE.getIS(1), 0.30F),
                new RecipeRandomOutput(EnumOreSecondary.LIME.getIS(1), 0.10F),
                new RecipeRandomOutput(EnumOreSecondary.COBALTITE.getIS(1), 0.05F)));

        RecipeManager.add(new OrelyzerRecipe(new ItemStack(Block.blockClay), 720,
                new RecipeRandomOutput(EnumOreSecondary.LITHIUM.getIS(1), 0.005F)));

        RecipeManager.add(new OrelyzerRecipe(new ItemStack(Block.oreLapis), 640,
                new RecipeRandomOutput(EnumOreSecondary.LAZURITE.getIS(1), 0.95F),
                new RecipeRandomOutput(new ItemStack(Item.dyePowder, 1, 4), 0.80F),  // lapis lazuli
                new RecipeRandomOutput(new ItemStack(Item.dyePowder, 1, 4), 0.20F)));

        RecipeManager.add(new OrelyzerRecipe(new ItemStack(Block.oreLapis), 640,
                new RecipeRandomOutput(EnumOreSecondary.BERYL.getIS(1), 0.85F),
                new RecipeRandomOutput(new ItemStack(Item.emerald), 0.10F)));
    }
}
