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
import num.complexwiring.world.ore.primary.BlockOrePrimary;
import num.complexwiring.world.ore.primary.EnumOrePrimary;
import num.complexwiring.world.ore.primary.ItemBlockOrePrimary;
import num.complexwiring.world.ore.secondary.BlockOreSecondary;
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

    public static Block orePrimary, oreSecondaryClassic, oreSecondaryRare, oreSecondaryVanilla;
    public static CreativeTabCW tabCWWorld = new CreativeTabCW("tabCWWorld", null);
    public static final int ORE_SECONDARY_CLASSIC_ID = 667;
    public static final int ORE_SECONDARY_RARE_ID = 668;
    public static final int ORE_SECONDARY_VANILLA_ID = 669;


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

        oreSecondaryClassic = new BlockOreSecondaryClassic(ORE_SECONDARY_CLASSIC_ID);
        ((BlockOreSecondary) oreSecondaryClassic).registerOres();
        GameRegistry.registerBlock(oreSecondaryClassic, ItemBlockOreSecondaryClassic.class, oreSecondaryClassic.getUnlocalizedName());

        oreSecondaryRare = new BlockOreSecondaryRare(ORE_SECONDARY_RARE_ID);
        ((BlockOreSecondary) oreSecondaryRare).registerOres();
        GameRegistry.registerBlock(oreSecondaryRare, ItemBlockOreSecondaryRare.class, oreSecondaryRare.getUnlocalizedName());

        oreSecondaryVanilla = new BlockOreSecondaryVanilla(ORE_SECONDARY_VANILLA_ID);
        ((BlockOreSecondary) oreSecondaryVanilla).registerOres();
        GameRegistry.registerBlock(oreSecondaryVanilla, ItemBlockOreSecondaryVanilla.class, oreSecondaryVanilla.getUnlocalizedName());
    }

    private void registerItems() {

    }

    private void registerRecipes() {
        RecipeManager.add(new OrelyzerRecipe(EnumOrePrimary.COPPER.getIS(1), 240,
               new RecipeRandomOutput(EnumOreSecondaryClassic.CHALCOCITE.getIS(1), 0.65F),
               new RecipeRandomOutput(EnumOreSecondaryClassic.CHALCOPYRITE.getIS(1), 0.35F),
               new RecipeRandomOutput(EnumOreSecondaryClassic.BORNITE.getIS(1), 0.25F),
               new RecipeRandomOutput(EnumOreSecondaryClassic.MALACHITE.getIS(1), 0.10F)));

        RecipeManager.add(new OrelyzerRecipe(EnumOrePrimary.TIN.getIS(1), 240,
                new RecipeRandomOutput(EnumOreSecondaryClassic.CASSITERITE.getIS(1), 0.75F),
                new RecipeRandomOutput(EnumOreSecondaryClassic.STANNITE.getIS(1), 0.40F)));

        RecipeManager.add(new OrelyzerRecipe(EnumOrePrimary.SILVER.getIS(1), 360,
                new RecipeRandomOutput(EnumOreSecondaryClassic.ARGENTITE.getIS(1), 0.80F),
                new RecipeRandomOutput(EnumOreSecondaryClassic.CHLORARGYRITE.getIS(1), 0.35F)));

        RecipeManager.add(new OrelyzerRecipe(EnumOrePrimary.LEAD.getIS(1), 360,
                new RecipeRandomOutput(EnumOreSecondaryClassic.GALENA.getIS(1), 0.75F),
                new RecipeRandomOutput(EnumOreSecondaryClassic.CERUSSITE.getIS(1), 0.25F),
                new RecipeRandomOutput(EnumOreSecondaryClassic.ANGLESITE.getIS(1), 0.20F)));

        RecipeManager.add(new OrelyzerRecipe(EnumOrePrimary.ALUMINIUM.getIS(1), 480,
                new RecipeRandomOutput(EnumOreSecondaryRare.BAUXITE.getIS(1), 0.75F),
                new RecipeRandomOutput(EnumOreSecondaryRare.CRYOLITE.getIS(1), 0.375F)));

        RecipeManager.add(new OrelyzerRecipe(EnumOrePrimary.URANIUM.getIS(1), 640,
                new RecipeRandomOutput(EnumOreSecondaryRare.URANINITE.getIS(1), 0.75F),
                new RecipeRandomOutput(EnumOreSecondaryRare.AUTUNITE.getIS(1), 0.15F)));

        RecipeManager.add(new OrelyzerRecipe(EnumOrePrimary.TUNGSTEN.getIS(1), 720,
                new RecipeRandomOutput(EnumOreSecondaryRare.WOLFRAMITE.getIS(1), 0.65F),
                new RecipeRandomOutput(EnumOreSecondaryRare.SCHEELITE.getIS(1), 0.45F)));

        RecipeManager.add(new OrelyzerRecipe(EnumOrePrimary.TITANIUM.getIS(1), 720,
                new RecipeRandomOutput(EnumOreSecondaryRare.ILMENITE.getIS(1), 0.60F),
                new RecipeRandomOutput(EnumOreSecondaryRare.RUTILE.getIS(1), 0.50F)));

        RecipeManager.add(new OrelyzerRecipe(new ItemStack(Block.oreGold), 360,
                new RecipeRandomOutput(EnumOreSecondaryRare.CALAVERITE.getIS(1), 0.35F),
                new RecipeRandomOutput(EnumOreSecondaryRare.SYLVANITE.getIS(1), 0.35F),
                new RecipeRandomOutput(EnumOreSecondaryRare.PUREGOLD.getIS(1), 0.55F)));

        RecipeManager.add(new OrelyzerRecipe(new ItemStack(Block.oreIron), 240,
                new RecipeRandomOutput(EnumOreSecondaryVanilla.MAGNETITE.getIS(1), 0.50F),
                new RecipeRandomOutput(EnumOreSecondaryVanilla.HEMATITE.getIS(1), 0.70F),
                new RecipeRandomOutput(EnumOreSecondaryVanilla.SIDERITE.getIS(1), 0.40F),
                new RecipeRandomOutput(EnumOreSecondaryVanilla.CHROMITE.getIS(1), 0.20F),
                new RecipeRandomOutput(EnumOreSecondaryVanilla.PENTLADITE.getIS(1), 0.20F)));

        RecipeManager.add(new OrelyzerRecipe(new ItemStack(Block.oreCoal), 320,
                new RecipeRandomOutput(EnumOreSecondaryVanilla.ANTHRACITE.getIS(1), 0.10F),
                new RecipeRandomOutput(EnumOreSecondaryVanilla.BITUMINOUS.getIS(1), 0.50F),
                new RecipeRandomOutput(EnumOreSecondaryVanilla.SUBBITUMINOUS.getIS(1), 0.75F),
                new RecipeRandomOutput(EnumOreSecondaryVanilla.LIGNITE.getIS(1), 0.15F),
                new RecipeRandomOutput(new ItemStack(Item.coal, 1), 0.15F)));

        RecipeManager.add(new OrelyzerRecipe(new ItemStack(Block.stone), 640,
                new RecipeRandomOutput(EnumOreSecondaryVanilla.PYROLUSITE.getIS(1), 0.05F),
                new RecipeRandomOutput(EnumOreSecondaryVanilla.CINNABAR.getIS(1), 0.05F),
                new RecipeRandomOutput(EnumOreSecondaryVanilla.DOLOMITE.getIS(1), 0.30F),
                new RecipeRandomOutput(EnumOreSecondaryVanilla.SPHALERITE.getIS(1), 0.05F),
                new RecipeRandomOutput(EnumOreSecondaryVanilla.LIMESTONE.getIS(1), 0.30F),
                new RecipeRandomOutput(EnumOreSecondaryVanilla.LIME.getIS(1), 0.10F),
                new RecipeRandomOutput(EnumOreSecondaryVanilla.COBALTITE.getIS(1), 0.05F)));

        RecipeManager.add(new OrelyzerRecipe(new ItemStack(Block.blockClay), 720,
                new RecipeRandomOutput(EnumOreSecondaryRare.LITHIUM.getIS(1), 0.005F)));

        RecipeManager.add(new OrelyzerRecipe(new ItemStack(Block.oreLapis), 640,
                new RecipeRandomOutput(EnumOreSecondaryRare.LAZURITE.getIS(1), 0.95F),
                new RecipeRandomOutput(new ItemStack(Item.dyePowder, 1, 4), 0.80F),  // lapis lazuli
                new RecipeRandomOutput(new ItemStack(Item.dyePowder, 1, 4), 0.20F)));

        RecipeManager.add(new OrelyzerRecipe(new ItemStack(Block.oreLapis), 640,
                new RecipeRandomOutput(EnumOreSecondaryRare.BERYL.getIS(1), 0.85F),
                new RecipeRandomOutput(new ItemStack(Item.emerald), 0.10F)));
    }
}
