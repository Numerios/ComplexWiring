package num.complexwiring.recipe;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class RecipeManager {

    private static List<Recipe> recipes = new ArrayList<Recipe>();

    public static void add(Recipe recipe) {
        recipes.add(recipe);
    }

    public static Recipe get(ItemStack is) {
        if (is != null) {
            for (Recipe recipe : recipes) {
                if (recipe.matches(is)) {
                    return recipe;
                }
            }
        }
        return null;
    }

    public static int toRecipeID(Recipe recipe) {
        if (recipes.contains(recipe)) {
            return recipes.indexOf(recipe);
        }
        return -1;
    }

    public static Recipe fromRecipeID(int ID) {
        if (recipes.size() < ID) {
            return recipes.get(ID);
        }
        return null;
    }
}
