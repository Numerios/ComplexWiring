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
import num.complexwiring.world.decor.dark.BlockDarkDecor;
import num.complexwiring.world.decor.dark.ItemBlockDarkDecor;
import num.complexwiring.world.decor.light.BlockLightDecor;
import num.complexwiring.world.decor.light.EnumLightDecor;
import num.complexwiring.world.decor.light.ItemBlockLightDecor;
import num.complexwiring.world.gen.WorldGenerator;
import num.complexwiring.world.industrial.stone.BlockStoneIndustrial;
import num.complexwiring.world.industrial.stone.ItemBlockStoneIndustrial;
import num.complexwiring.world.industrial.wood.BlockWoodIndustrial;
import num.complexwiring.world.industrial.wood.ItemBlockWoodIndustrial;
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
    public static Block orePrimary, oreSecondaryClassic, oreSecondaryRare, oreSecondaryVanilla, decorLight, decorDark, woodIndustrial, stoneIndustrial;
    public static CreativeTabCW tabCWWorld = new CreativeTabCW("tabCWWorld", null);

    @Override
    public void preInit() {
        registerBlocks();
        tabCWWorld.setIcon(EnumLightDecor.LIMESTONE.getIS(1));
    }

    @Override
    public void init() {
        registerOres();
        registerVanillaRecipes();
        registerCWRecipes();
        new WorldGenerator();
    }

    private void registerBlocks() {
        orePrimary = new BlockOrePrimary();
        GameRegistry.registerBlock(orePrimary, ItemBlockOrePrimary.class, orePrimary.getUnlocalizedName());

        oreSecondaryClassic = new BlockOreSecondaryClassic();
        GameRegistry.registerBlock(oreSecondaryClassic, ItemBlockOreSecondaryClassic.class, oreSecondaryClassic.getUnlocalizedName());

        oreSecondaryRare = new BlockOreSecondaryRare();
        GameRegistry.registerBlock(oreSecondaryRare, ItemBlockOreSecondaryRare.class, oreSecondaryRare.getUnlocalizedName());

        oreSecondaryVanilla = new BlockOreSecondaryVanilla();
        GameRegistry.registerBlock(oreSecondaryVanilla, ItemBlockOreSecondaryVanilla.class, oreSecondaryVanilla.getUnlocalizedName());

        decorLight = new BlockLightDecor();
        GameRegistry.registerBlock(decorLight, ItemBlockLightDecor.class, decorLight.getUnlocalizedName());

        decorDark = new BlockDarkDecor();
        GameRegistry.registerBlock(decorDark, ItemBlockDarkDecor.class, decorDark.getUnlocalizedName());

        woodIndustrial = new BlockWoodIndustrial();
        GameRegistry.registerBlock(woodIndustrial, ItemBlockWoodIndustrial.class, woodIndustrial.getUnlocalizedName());

        stoneIndustrial = new BlockStoneIndustrial();
        GameRegistry.registerBlock(stoneIndustrial, ItemBlockStoneIndustrial.class, stoneIndustrial.getUnlocalizedName());
    }

    private void registerOres() {
        ((BlockOrePrimary) orePrimary).registerOres();
        ((BlockOreSecondaryClassic) oreSecondaryClassic).registerOres();
        ((BlockOreSecondaryRare) oreSecondaryRare).registerOres();
        ((BlockOreSecondaryVanilla) oreSecondaryVanilla).registerOres();
        ((BlockLightDecor) decorLight).registerOres();
        ((BlockDarkDecor) decorDark).registerOres();
        ((BlockWoodIndustrial) woodIndustrial).registerOres();
        ((BlockStoneIndustrial) stoneIndustrial).registerOres();
    }

    private void registerVanillaRecipes() {
     /*   GameRegistry.addSmelting(EnumOrePrimary.COPPER.getIS(1), EnumIngot.COPPER.getIS(1), 0.75F);
        GameRegistry.addSmelting(EnumOrePrimary.TIN.getIS(1), EnumIngot.TIN.getIS(1), 0.75F);
        GameRegistry.addSmelting(EnumOrePrimary.SILVER.getIS(1), EnumIngot.SILVER.getIS(1), 0.75F);
        GameRegistry.addSmelting(EnumOrePrimary.LEAD.getIS(1), EnumIngot.LEAD.getIS(1), 0.75F);

        GameRegistry.addSmelting(EnumLightDecor.LIMESTONE_ROUGH.getIS(1), EnumLightDecor.LIMESTONE.getIS(1), 0.6F);
        GameRegistry.addSmelting(EnumLightDecor.DOLOMITE_ROUGH.getIS(1), EnumLightDecor.DOLOMITE.getIS(1), 0.6F);
        GameRegistry.addSmelting(EnumLightDecor.ARENITE_ROUGH.getIS(1), EnumLightDecor.ARENITE.getIS(1), 0.6F);
        GameRegistry.addSmelting(EnumLightDecor.DOLOMITE.getIS(1), EnumLightDecor.DARKDOLOMITE.getIS(1), 0.45F);

        GameRegistry.addShapedRecipe(EnumLightDecor.LIMESTONE_BRICK.getIS(4), "##", "##", '#', EnumLightDecor.LIMESTONE.getIS(1));
        GameRegistry.addShapedRecipe(EnumLightDecor.DOLOMITE_BRICK.getIS(4), "##", "##", '#', EnumLightDecor.DOLOMITE.getIS(1));
        GameRegistry.addShapedRecipe(EnumLightDecor.ARENITE_BRICK.getIS(4), "##", "##", '#', EnumLightDecor.ARENITE.getIS(1));
        GameRegistry.addShapedRecipe(EnumLightDecor.DARKDOLOMITE_BRICK.getIS(4), "##", "##", '#', EnumLightDecor.DARKDOLOMITE.getIS(1));

        GameRegistry.addShapedRecipe(EnumLightDecor.LIMESTONE_SMALLBRICK.getIS(4), "##", "##", '#', EnumLightDecor.LIMESTONE_BRICK.getIS(1));
        GameRegistry.addShapedRecipe(EnumLightDecor.DOLOMITE_SMALLBRICK.getIS(4), "##", "##", '#', EnumLightDecor.DOLOMITE_BRICK.getIS(1));
        GameRegistry.addShapedRecipe(EnumLightDecor.ARENITE_SMALLBRICK.getIS(4), "##", "##", '#', EnumLightDecor.ARENITE_BRICK.getIS(1));
        GameRegistry.addShapedRecipe(EnumLightDecor.DARKDOLOMITE_SMALLBRICK.getIS(4), "##", "##", '#', EnumLightDecor.DARKDOLOMITE_BRICK.getIS(1));

        GameRegistry.addShapelessRecipe(EnumLightDecor.LIMESTONE.getIS(1), EnumLightDecor.LIMESTONE_BRICK.getIS(1));
        GameRegistry.addShapelessRecipe(EnumLightDecor.DOLOMITE.getIS(1), EnumLightDecor.DOLOMITE_BRICK.getIS(1));
        GameRegistry.addShapelessRecipe(EnumLightDecor.ARENITE.getIS(1), EnumLightDecor.ARENITE_BRICK.getIS(1));
        GameRegistry.addShapelessRecipe(EnumLightDecor.DARKDOLOMITE.getIS(1), EnumLightDecor.DARKDOLOMITE_BRICK.getIS(1));

        GameRegistry.addShapelessRecipe(EnumLightDecor.LIMESTONE_BRICK.getIS(1), EnumLightDecor.LIMESTONE_SMALLBRICK.getIS(1));
        GameRegistry.addShapelessRecipe(EnumLightDecor.DOLOMITE_BRICK.getIS(1), EnumLightDecor.DOLOMITE_SMALLBRICK.getIS(1));
        GameRegistry.addShapelessRecipe(EnumLightDecor.ARENITE_BRICK.getIS(1), EnumLightDecor.ARENITE_SMALLBRICK.getIS(1));
        GameRegistry.addShapelessRecipe(EnumLightDecor.DARKDOLOMITE_BRICK.getIS(1), EnumLightDecor.DARKDOLOMITE_SMALLBRICK.getIS(1));

        GameRegistry.addShapelessRecipe(EnumLightDecor.LIMESTONE_ROUGH.getIS(1), EnumLightDecor.LIMESTONE.getIS(1));
        GameRegistry.addShapelessRecipe(EnumLightDecor.DOLOMITE_ROUGH.getIS(1), EnumLightDecor.DOLOMITE.getIS(1));
        GameRegistry.addShapelessRecipe(EnumLightDecor.ARENITE_ROUGH.getIS(1), EnumLightDecor.ARENITE.getIS(1));

        GameRegistry.addShapelessRecipe(EnumLightDecor.DOLOMITE_DARKDOLOMITE_MIX.getIS(4), EnumLightDecor.DOLOMITE.getIS(1), EnumLightDecor.DOLOMITE.getIS(1), EnumLightDecor.DARKDOLOMITE.getIS(1), EnumLightDecor.DARKDOLOMITE.getIS(1)); */
    }

    private void registerCWRecipes() {
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
                new RecipeRandomOutput(EnumLightDecor.DOLOMITE.getIS(1), 0.20F),
                new RecipeRandomOutput(EnumLightDecor.LIMESTONE.getIS(1), 0.20F),
                new RecipeRandomOutput(EnumLightDecor.ARENITE.getIS(1), 0.20F)));

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
