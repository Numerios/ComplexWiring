package num.complexwiring.api.recipe;

import cpw.mods.fml.common.Loader;

import java.lang.reflect.InvocationTargetException;
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
            } catch (NoSuchMethodException e) {
                System.err.println("[CW-API] Couldn't add a recipe remotely!");
            } catch (ClassNotFoundException e) {
                System.err.println("[CW-API] Couldn't add a recipe remotely!");
            } catch (InvocationTargetException e) {
                System.err.println("[CW-API] Couldn't add a recipe remotely!");
            } catch (IllegalAccessException e) {
                System.err.println("[CW-API] Couldn't add a recipe remotely!");
            }
        }
        return false;
    }
}
