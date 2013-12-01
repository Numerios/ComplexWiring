package num.complexwiring.recipe;

import net.minecraft.item.ItemStack;

public class Recipe {

    private ItemStack input;
    private RecipeOutput[] outputs;

    public Recipe(ItemStack input, RecipeOutput... outputs) {
        this.input = input;
        this.outputs = outputs;
    }

    public boolean matches(ItemStack is) {
        return input.isItemEqual(is);
    }

    public void setOutputs(RecipeOutput... outputs) {
        this.outputs = outputs;
    }

    public ItemStack getInput(){
        return input;
    }

    public RecipeOutput[] getOutputs(){
        return outputs;
    }

    @Override
    public String toString() {
        return "CW_Recipe: " + input.toString() + " >> " + outputs.toString();
    }
}
