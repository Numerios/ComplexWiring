package num.complexwiring.world;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import num.complexwiring.api.recipe.OrelyzerRecipe;
import num.complexwiring.api.recipe.RecipeRandomOutput;
import num.complexwiring.core.CreativeTabCW;
import num.complexwiring.lib.Module;
import num.complexwiring.recipe.RecipeManager;
import num.complexwiring.world.decor.BlockDecor;
import num.complexwiring.world.decor.EnumDecor;
import num.complexwiring.world.decor.ItemBlockDecor;
import num.complexwiring.world.gen.WorldGenerator;
import num.complexwiring.world.ore.primary.BlockOrePrimary;
import num.complexwiring.world.ore.primary.EnumOrePrimary;
import num.complexwiring.world.ore.primary.ItemBlockOrePrimary;
import num.complexwiring.world.ore.secondary.classic.BlockOreSecondaryClassic;
import num.complexwiring.world.ore.secondary.classic.EnumOreSecondaryClassic;
import num.complexwiring.world.ore.secondary.classic.ItemBlockOreSecondaryClassic;
import num.complexwiring.world.ore.secondary.rare.BlockOreSecondaryRare;
import num.complexwiring.world.ore.secondary.rare.EnumOreSecondaryRare;
import num.complexwiring.world.ore.secondary.rare.ItemBlockOreSecondaryRare;
import num.complexwiring.world.ore.secondary.vanilla.BlockOreSecondaryVanilla;
import num.complexwiring.world.ore.secondary.vanilla.EnumOreSecondaryVanilla;
import num.complexwiring.world.ore.secondary.vanilla.ItemBlockOreSecondaryVanilla;

public class ModuleWorld extends Module {
    public static Block orePrimary, oreSecondaryClassic, oreSecondaryRare, oreSecondaryVanilla, decor;
    public static CreativeTabCW tabCWWorld = new CreativeTabCW("tabCWWorld", null);

    @Override
    public void preInit() {
        registerBlocks();
        registerItems();
        tabCWWorld.setIcon(EnumDecor.LIMESTONE.getIS(1));
    }

    @Override
    public void init() {
        registerRecipes();
        new WorldGenerator();
    }

    private void registerBlocks() {
        //TODO: CONFIGS!
        orePrimary = new BlockOrePrimary();
        ((BlockOrePrimary) orePrimary).registerOres();
        GameRegistry.registerBlock(orePrimary, ItemBlockOrePrimary.class, orePrimary.getUnlocalizedName());

        oreSecondaryClassic = new BlockOreSecondaryClassic();
        ((BlockOreSecondaryClassic) oreSecondaryClassic).registerOres();
        GameRegistry.registerBlock(oreSecondaryClassic, ItemBlockOreSecondaryClassic.class, oreSecondaryClassic.getUnlocalizedName());

        oreSecondaryRare = new BlockOreSecondaryRare();
        ((BlockOreSecondaryRare) oreSecondaryRare).registerOres();
        GameRegistry.registerBlock(oreSecondaryRare, ItemBlockOreSecondaryRare.class, oreSecondaryRare.getUnlocalizedName());

        oreSecondaryVanilla = new BlockOreSecondaryVanilla();
        ((BlockOreSecondaryVanilla) oreSecondaryVanilla).registerOres();
        GameRegistry.registerBlock(oreSecondaryVanilla, ItemBlockOreSecondaryVanilla.class, oreSecondaryVanilla.getUnlocalizedName());

        decor = new BlockDecor();
        ((BlockDecor) decor).registerOres();
        GameRegistry.registerBlock(decor, ItemBlockDecor.class, decor.getUnlocalizedName());
    }

    private void registerItems() {

    }

    private void registerRecipes() {
        RecipeManager.add(new OrelyzerRecipe(EnumOrePrimary.COPPER.getIS(1), 240,
                new RecipeRandomOutput(EnumOreSecondaryClassic.CHALCOCITE.getIS(1), 0.50F),
                new RecipeRandomOutput(EnumOreSecondaryClassic.BORNITE.getIS(1), 0.20F),
                new RecipeRandomOutput(EnumOreSecondaryClassic.CHALCOPYRITE.getIS(1), 0.35F),
                new RecipeRandomOutput(EnumOreSecondaryClassic.MALACHITE.getIS(1), 0.20F)));

        RecipeManager.add(new OrelyzerRecipe(EnumOrePrimary.TIN.getIS(1), 240,
                new RecipeRandomOutput(EnumOreSecondaryClassic.CASSITERITE.getIS(1), 0.70F),
                new RecipeRandomOutput(EnumOreSecondaryClassic.STANNITE.getIS(1), 0.40F)));

        RecipeManager.add(new OrelyzerRecipe(EnumOrePrimary.SILVER.getIS(1), 360,
                new RecipeRandomOutput(EnumOreSecondaryClassic.ARGENTITE.getIS(1), 0.75F),
                new RecipeRandomOutput(EnumOreSecondaryClassic.CHLORARGYRITE.getIS(1), 0.35F)));

        RecipeManager.add(new OrelyzerRecipe(EnumOrePrimary.LEAD.getIS(1), 360,
                new RecipeRandomOutput(EnumOreSecondaryClassic.GALENA.getIS(1), 0.675F),
                new RecipeRandomOutput(EnumOreSecondaryClassic.CERUSSITE.getIS(1), 0.35F),
                new RecipeRandomOutput(EnumOreSecondaryClassic.ANGLESITE.getIS(1), 0.20F)));

        RecipeManager.add(new OrelyzerRecipe(EnumOrePrimary.ALUMINIUM.getIS(1), 480,
                new RecipeRandomOutput(EnumOreSecondaryRare.BAUXITE.getIS(1), 0.725F),
                new RecipeRandomOutput(EnumOreSecondaryRare.CRYOLITE.getIS(1), 0.425F)));

        RecipeManager.add(new OrelyzerRecipe(EnumOrePrimary.URANIUM.getIS(1), 640,
                new RecipeRandomOutput(EnumOreSecondaryRare.URANINITE.getIS(1), 0.65F),
                new RecipeRandomOutput(EnumOreSecondaryRare.AUTUNITE.getIS(1), 0.25F)));

        RecipeManager.add(new OrelyzerRecipe(EnumOrePrimary.TUNGSTEN.getIS(1), 720,
                new RecipeRandomOutput(EnumOreSecondaryRare.WOLFRAMITE.getIS(1), 0.7125F),
                new RecipeRandomOutput(EnumOreSecondaryRare.SCHEELITE.getIS(1), 0.45F)));

        RecipeManager.add(new OrelyzerRecipe(EnumOrePrimary.TITANIUM.getIS(1), 720,
                new RecipeRandomOutput(EnumOreSecondaryRare.ILMENITE.getIS(1), 0.655F),
                new RecipeRandomOutput(EnumOreSecondaryRare.RUTILE.getIS(1), 0.55F)));

        RecipeManager.add(new OrelyzerRecipe(new ItemStack(Blocks.gold_ore), 360,
                new RecipeRandomOutput(EnumOreSecondaryRare.CALAVERITE.getIS(1), 0.275F),
                new RecipeRandomOutput(EnumOreSecondaryRare.SYLVANITE.getIS(1), 0.275F),
                new RecipeRandomOutput(EnumOreSecondaryRare.PUREGOLD.getIS(1), 0.50F)));

        RecipeManager.add(new OrelyzerRecipe(new ItemStack(Blocks.iron_ore), 240,
                new RecipeRandomOutput(EnumOreSecondaryVanilla.MAGNETITE.getIS(1), 0.25F),
                new RecipeRandomOutput(EnumOreSecondaryVanilla.HEMATITE.getIS(1), 0.55F),
                new RecipeRandomOutput(EnumOreSecondaryVanilla.SIDERITE.getIS(1), 0.35F),
                new RecipeRandomOutput(EnumOreSecondaryVanilla.CHROMITE.getIS(1), 0.10F),
                new RecipeRandomOutput(EnumOreSecondaryVanilla.PENTLADITE.getIS(1), 0.10F)));

        RecipeManager.add(new OrelyzerRecipe(new ItemStack(Blocks.coal_ore), 320,
                new RecipeRandomOutput(EnumOreSecondaryVanilla.ANTHRACITE.getIS(1), 0.10F),
                new RecipeRandomOutput(EnumOreSecondaryVanilla.BITUMINOUS.getIS(1), 0.35F),
                new RecipeRandomOutput(EnumOreSecondaryVanilla.SUBBITUMINOUS.getIS(1), 0.50F),
                new RecipeRandomOutput(EnumOreSecondaryVanilla.LIGNITE.getIS(1), 0.10F),
                new RecipeRandomOutput(new ItemStack(Items.coal, 1), 0.075F)));

        RecipeManager.add(new OrelyzerRecipe(new ItemStack(Blocks.stone), 640,
                new RecipeRandomOutput(new ItemStack(Blocks.cobblestone), 0.35F),
                new RecipeRandomOutput(new ItemStack(Blocks.sand), 0.10F),
                new RecipeRandomOutput(new ItemStack(Blocks.gravel), 0.15F),
                new RecipeRandomOutput(EnumOreSecondaryVanilla.PYROLUSITE.getIS(1), 0.075F),
                new RecipeRandomOutput(EnumOreSecondaryVanilla.CINNABAR.getIS(1), 0.075F),
                new RecipeRandomOutput(EnumOreSecondaryVanilla.SPHALERITE.getIS(1), 0.075F),
                new RecipeRandomOutput(EnumOreSecondaryVanilla.COBALTITE.getIS(1), 0.075F),
                new RecipeRandomOutput(EnumDecor.DOLOMITE.getIS(1), 0.20F),
                new RecipeRandomOutput(EnumDecor.LIMESTONE.getIS(1), 0.20F),
                new RecipeRandomOutput(EnumDecor.ARENITE.getIS(1), 0.20F)));

        RecipeManager.add(new OrelyzerRecipe(new ItemStack(Blocks.clay), 720,
                new RecipeRandomOutput(EnumOreSecondaryRare.LITHIUM.getIS(1), 0.15F)));

        RecipeManager.add(new OrelyzerRecipe(new ItemStack(Blocks.lapis_ore), 640,
                new RecipeRandomOutput(EnumOreSecondaryRare.LAZURITE.getIS(1), 0.90F),
                new RecipeRandomOutput(new ItemStack(Items.dye, 1, 4), 0.60F),  // lapis lazuli
                new RecipeRandomOutput(new ItemStack(Items.dye, 1, 4), 0.10F)));

        RecipeManager.add(new OrelyzerRecipe(new ItemStack(Blocks.emerald_ore), 640,
                new RecipeRandomOutput(EnumOreSecondaryRare.BERYL.getIS(1), 0.875F),
                new RecipeRandomOutput(new ItemStack(Items.emerald), 0.475F)));
    }
}
