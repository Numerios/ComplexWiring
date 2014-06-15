package num.complexwiring.recipe;

import net.minecraft.item.ItemStack;
import num.complexwiring.api.recipe.CrusherRecipe;
import num.complexwiring.api.recipe.ICWRecipe;
import num.complexwiring.api.recipe.OrelyzerRecipe;
import num.complexwiring.api.recipe.SmasherRecipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeManager {

    private static List<OrelyzerRecipe> orelyzerRecipes = new ArrayList<OrelyzerRecipe>();
    private static List<CrusherRecipe> crusherRecipes = new ArrayList<CrusherRecipe>();
    private static List<SmasherRecipe> smasherRecipes = new ArrayList<SmasherRecipe>();

    public static void add(ICWRecipe recipe) {
        if (recipe.getType() == Type.ORELYZER) orelyzerRecipes.add((OrelyzerRecipe) recipe);
        else if (recipe.getType() == Type.CRUSHER) crusherRecipes.add((CrusherRecipe) recipe);
        else if (recipe.getType() == Type.SMASHER) smasherRecipes.add((SmasherRecipe) recipe);
    }

    public static ICWRecipe get(Type type, ItemStack is) {
        if (is != null) {
            if (type == Type.ORELYZER) {
                for (OrelyzerRecipe orelyzerRecipe : orelyzerRecipes) {
                    if (orelyzerRecipe.matches(is)) return orelyzerRecipe;
                }
            } else if (type == Type.CRUSHER) {
                for (CrusherRecipe crusherRecipe : crusherRecipes) {
                    if (crusherRecipe.matches(is)) return crusherRecipe;
                }
            } else if (type == Type.SMASHER) {
                for (SmasherRecipe smasherRecipe : smasherRecipes) {
                    if (smasherRecipe.matches(is)) return smasherRecipe;
                }
            }
        }
        return null;
    }

    public static int toRecipeID(Type type, ICWRecipe recipe) {
        if (type == Type.ORELYZER && orelyzerRecipes.contains(recipe)) return orelyzerRecipes.indexOf(recipe);
        else if (type == Type.CRUSHER && crusherRecipes.contains(recipe)) return crusherRecipes.indexOf(recipe);
        else if (type == Type.SMASHER && smasherRecipes.contains(recipe)) return smasherRecipes.indexOf(recipe);

        else return -1;
    }

    public static ICWRecipe fromRecipeID(Type type, int ID) {
        if (type == Type.ORELYZER && orelyzerRecipes.size() < ID) return orelyzerRecipes.get(ID);
        else if (type == Type.CRUSHER && crusherRecipes.size() < ID) return crusherRecipes.get(ID);
        else if (type == Type.SMASHER && smasherRecipes.size() < ID) return smasherRecipes.get(ID);
        return null;
    }

    public enum Type {
        ORELYZER,
        CRUSHER,
        SMASHER
    }
}
