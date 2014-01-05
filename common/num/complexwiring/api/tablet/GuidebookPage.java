package num.complexwiring.api.tablet;

import net.minecraft.item.crafting.IRecipe;
import num.complexwiring.api.recipe.ICWRecipe;
import num.complexwiring.api.research.IResearch;

public class GuidebookPage {
    public static enum Type {
        TEXT, RESEARCH, VANILLA_CRAFTING, MOD_CRAFTING
    }

    public IResearch research = null;
    public Type type = Type.TEXT;
    public String text = null;
    public IRecipe vanillaRecipe = null;
    public ICWRecipe modRecipe = null;

    public GuidebookPage(String text){
        this.type = Type.TEXT;
        this.text = text;
    }

    public GuidebookPage(IResearch research){
        this.type = Type.RESEARCH;
        this.research = research;
    }

    public GuidebookPage(IRecipe recipe){
        this.type = Type.VANILLA_CRAFTING;
        this.vanillaRecipe = recipe;
    }

    public GuidebookPage(ICWRecipe recipe){
        this.type = Type.MOD_CRAFTING;
        this.modRecipe = recipe;
    }
}
