package num.complexwiring.machine;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import num.complexwiring.lib.Module;
import num.complexwiring.recipe.OrelyzerRecipe;
import num.complexwiring.recipe.RecipeManager;
import num.complexwiring.api.recipe.RecipeRandomOutput;

public class ModuleMachine extends Module {
    public static Block machine;

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
        machine = new BlockMachine(669);
        GameRegistry.registerBlock(machine, ItemBlockMachine.class, machine.getUnlocalizedName());
        ((BlockMachine) machine).registerTiles();
    }

    private void registerItems() {

    }

    private void registerRecipes() {
        RecipeManager.add(new OrelyzerRecipe(new ItemStack(Block.wood, 1), 120,
                new RecipeRandomOutput(new ItemStack(Item.diamond, 1), 0.1F),
                new RecipeRandomOutput(new ItemStack(Item.stick, 2), 0.8F),
                new RecipeRandomOutput(new ItemStack(Item.stick, 1), 1F)));
    }
}
