package num.complexwiring.api.recipe;

import net.minecraft.item.ItemStack;
import num.complexwiring.recipe.RecipeManager;

import java.util.List;
import java.util.Random;

/**
 * A base interface for all CW recipes (needed to identify recipe classes)
 */
public interface ICWRecipe {

    public ItemStack getInput();

    public boolean matches(ItemStack is);

    public int getNeededPower();

    public List<ItemStack> getOutput(Random rand);

    public RecipeManager.Type getType();
}
