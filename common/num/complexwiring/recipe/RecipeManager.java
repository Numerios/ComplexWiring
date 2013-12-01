package num.complexwiring.recipe;

import net.minecraft.item.Item;
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

    public static void addAllRecipes() {
        add(new Recipe(new ItemStack(Item.appleGold, 1), new RecipeOutput(new ItemStack(Item.appleRed, 1), 0.8F), new RecipeOutput(new ItemStack(Item.goldNugget, 3), 0.2F)));
    }
}
