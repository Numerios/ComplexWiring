package num.complexwiring.mechanical;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import num.complexwiring.api.recipe.SmasherRecipe;
import num.complexwiring.base.EnumComponent;
import num.complexwiring.base.EnumMaterial;
import num.complexwiring.core.CreativeTabCW;
import num.complexwiring.lib.Module;
import num.complexwiring.mechanical.smasher.BlockSmasher;
import num.complexwiring.mechanical.smasher.ItemBlockSmasher;
import num.complexwiring.mechanical.storagebox.BlockStorageBox;
import num.complexwiring.mechanical.storagebox.EnumStorageBox;
import num.complexwiring.mechanical.storagebox.ItemBlockStorageBox;
import num.complexwiring.recipe.RecipeManager;

public class ModuleMechanical extends Module {
    public static Block storageBox, smasher;
    public static CreativeTabCW tabCWMechanical = new CreativeTabCW("tabCWMechanical", null);

    @Override
    public void preInit() {
        registerBlocks();
        registerItems();
        tabCWMechanical.setIcon(new ItemStack(smasher));
    }

    @Override
    public void init() {
        registerRecipes();
    }

    private void registerBlocks() {
        storageBox = new BlockStorageBox();
        GameRegistry.registerBlock(storageBox, ItemBlockStorageBox.class, storageBox.getUnlocalizedName());
        ((BlockStorageBox) storageBox).registerTiles();

        smasher = new BlockSmasher();
        GameRegistry.registerBlock(smasher, ItemBlockSmasher.class, smasher.getUnlocalizedName());
        ((BlockSmasher) smasher).registerTiles();
    }

    private void registerItems() {
    }

    private void registerRecipes() {
        for (EnumMaterial material : EnumMaterial.VALID) {
            RecipeManager.add(new SmasherRecipe(material.getOre(), 180, material.getDust(2)));
        }

        GameRegistry.addRecipe(new ShapedOreRecipe(EnumStorageBox.BASIC.getIS(1), "PCP", "C#C", "PCP", 'P', "plankWood", 'C', "cobblestone", '#', Blocks.chest));
        GameRegistry.addRecipe(new ShapedOreRecipe(EnumStorageBox.BASIC.getIS(1), "CPC", "P#P", "CPC", 'P', "plankWood", 'C', "cobblestone", '#', Blocks.chest));

        GameRegistry.addRecipe(new ShapedOreRecipe(EnumStorageBox.ADVANCED.getIS(1), " I ", "I#I", " I ", 'I', "ingotIron", '#', EnumStorageBox.BASIC.getIS(1)));

        GameRegistry.addRecipe(new ShapedOreRecipe(EnumStorageBox.ADVANCED.getIS(1), "PIP", "I#I", "PIP", 'P', "plankWood", 'I', "ingotIron", '#', Blocks.chest));
        GameRegistry.addRecipe(new ShapedOreRecipe(EnumStorageBox.ADVANCED.getIS(1), "IPI", "P#P", "IPI", 'P', "plankWood", 'I', "ingotIron", '#', Blocks.chest));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(smasher), "FFF", "PSP", "CCC", 'F', Items.flint, 'P', "plankWood", 'C', "cobblestone", 'S', Items.stick));

        RecipeManager.add(new SmasherRecipe(new ItemStack(Blocks.log, 1, 0), 90, EnumComponent.WOOD_PULP.getIS(10)));
        RecipeManager.add(new SmasherRecipe(new ItemStack(Blocks.log, 1, 1), 90, EnumComponent.WOOD_PULP.getIS(10)));
        RecipeManager.add(new SmasherRecipe(new ItemStack(Blocks.log, 1, 2), 90, EnumComponent.WOOD_PULP.getIS(10)));
        RecipeManager.add(new SmasherRecipe(new ItemStack(Blocks.log, 1, 3), 90, EnumComponent.WOOD_PULP.getIS(10)));
        RecipeManager.add(new SmasherRecipe(new ItemStack(Blocks.log2, 1, 0), 90, EnumComponent.WOOD_PULP.getIS(10)));
        RecipeManager.add(new SmasherRecipe(new ItemStack(Blocks.log2, 1, 1), 90, EnumComponent.WOOD_PULP.getIS(10)));

        RecipeManager.add(new SmasherRecipe(new ItemStack(Blocks.planks, 1, 0), 60, EnumComponent.WOOD_PULP.getIS(2)));
        RecipeManager.add(new SmasherRecipe(new ItemStack(Blocks.planks, 1, 1), 60, EnumComponent.WOOD_PULP.getIS(2)));
        RecipeManager.add(new SmasherRecipe(new ItemStack(Blocks.planks, 1, 2), 60, EnumComponent.WOOD_PULP.getIS(2)));
        RecipeManager.add(new SmasherRecipe(new ItemStack(Blocks.planks, 1, 3), 60, EnumComponent.WOOD_PULP.getIS(2)));
        RecipeManager.add(new SmasherRecipe(new ItemStack(Blocks.planks, 1, 4), 60, EnumComponent.WOOD_PULP.getIS(2)));
        RecipeManager.add(new SmasherRecipe(new ItemStack(Blocks.planks, 1, 5), 60, EnumComponent.WOOD_PULP.getIS(2)));

        RecipeManager.add(new SmasherRecipe(new ItemStack(Blocks.gravel), 45, new ItemStack(Items.flint)));
    }
}
