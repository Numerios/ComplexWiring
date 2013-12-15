package num.complexwiring.api.recipe;

import cpw.mods.fml.common.Loader;

import java.lang.reflect.Method;

public class Recipes {

    @SuppressWarnings("unchecked")
    public static boolean addRecipe(ICWRecipe recipe) {
        if (Loader.isModLoaded("ComplexWiring")) {
            try {
                Class recipeManager = Class.forName("num.complexwiring.recipe.RecipeManager");
                Method add = recipeManager.getMethod("add", ICWRecipe.class);
                add.invoke(null, recipe);
                return true;
            } catch (Exception e) {
                System.err.println("[CW] [API] Couldn't add a recipe remotely! " + e.printStackTrace());
            }
        }
        return false;
    }
}
