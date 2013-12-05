package num.complexwiring.recipe;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class RecipeManager {

    private static List<OrelyzerRecipe> orelyzerRecipes = new ArrayList<OrelyzerRecipe>();

    public static void add(OrelyzerRecipe orelyzerRecipe) {
        orelyzerRecipes.add(orelyzerRecipe);
    }

    public static OrelyzerRecipe get(ItemStack is) {
        if (is != null) {
            for (OrelyzerRecipe orelyzerRecipe : orelyzerRecipes) {
                if (orelyzerRecipe.matches(is)) {
                    return orelyzerRecipe;
                }
            }
        }
        return null;
    }

    public static int toRecipeID(OrelyzerRecipe recipe) {
        if (orelyzerRecipes.contains(recipe)) {
            return orelyzerRecipes.indexOf(recipe);
        }
        return -1;
    }

    public static OrelyzerRecipe fromRecipeID(int ID) {
        if (orelyzerRecipes.size() < ID) {
            return orelyzerRecipes.get(ID);
        }
        return null;
    }
}
