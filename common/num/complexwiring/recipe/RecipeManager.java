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
        switch (recipe.getType()) {
            case ORELYZER: {
                if (!orelyzerRecipes.contains(recipe)) orelyzerRecipes.add((OrelyzerRecipe) recipe);
                return;
            }
            case CRUSHER: {
                if (!crusherRecipes.contains(recipe)) crusherRecipes.add((CrusherRecipe) recipe);
                break;
            }
            case SMASHER: {
                if (!smasherRecipes.contains(recipe)) smasherRecipes.add((SmasherRecipe) recipe);
                break;
            }
        }
    }

    public static boolean contains(ICWRecipe recipe) {
        switch (recipe.getType()) {
            case ORELYZER:
                return orelyzerRecipes.contains(recipe);
            case CRUSHER:
                return crusherRecipes.contains(recipe);
            case SMASHER:
                return smasherRecipes.contains(recipe);
        }
        return false;
    }

    public static ICWRecipe get(Type type, ItemStack is) {
        if (is != null) {
            switch (type) {
                case ORELYZER: {
                    for (OrelyzerRecipe orelyzerRecipe : orelyzerRecipes) {
                        if (orelyzerRecipe.matches(is)) return orelyzerRecipe;
                    }
                }
                case CRUSHER: {
                    for (CrusherRecipe crusherRecipe : crusherRecipes) {
                        if (crusherRecipe.matches(is)) return crusherRecipe;
                    }
                }
                case SMASHER: {
                    for (SmasherRecipe smasherRecipe : smasherRecipes) {
                        if (smasherRecipe.matches(is)) return smasherRecipe;
                    }
                }
            }
        }
        return null;
    }

    public static int toRecipeID(Type type, ICWRecipe recipe) {
        switch (type) {
            case ORELYZER:
                if (orelyzerRecipes.contains(recipe)) return orelyzerRecipes.indexOf(recipe);
            case CRUSHER:
                if (crusherRecipes.contains(recipe)) return crusherRecipes.indexOf(recipe);
            case SMASHER:
                if (smasherRecipes.contains(recipe)) return smasherRecipes.indexOf(recipe);
        }
        return -1;
    }

    public static ICWRecipe fromRecipeID(Type type, int ID) {
        switch (type) {
            case ORELYZER:
                if (orelyzerRecipes.size() < ID) return orelyzerRecipes.get(ID);
            case CRUSHER:
                if (orelyzerRecipes.size() < ID) return orelyzerRecipes.get(ID);
            case SMASHER:
                if (smasherRecipes.size() < ID) return smasherRecipes.get(ID);
        }
        return null;
    }

    public enum Type {
        ORELYZER,
        CRUSHER,
        SMASHER
    }
}
