package num.complexwiring.api.recipe;

import net.minecraft.item.ItemStack;
import num.complexwiring.recipe.RecipeManager;

import java.util.ArrayList;
import java.util.Random;

/**
 * A base interface for all CW recipes (needed to identify recipe classes)
 */
public interface ICWRecipe {

    public ItemStack getInput();

    public boolean matches(ItemStack is);

    public int getNeededPower();

    public RecipeRandomOutput[] getOutputs();

    public ArrayList<ItemStack> getCompleteOutput(Random rand);

    public RecipeManager.Type getType();

}
