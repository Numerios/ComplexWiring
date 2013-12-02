package num.complexwiring.recipe;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public ItemStack getInput() {
        return input;
    }

    public RecipeOutput[] getOutputs() {
        return outputs;
    }

    public void setOutputs(RecipeOutput... outputs) {
        this.outputs = outputs;
    }

    public ItemStack[] getCompleteOutput(Random rand) {
        List<ItemStack> completeOutput = new ArrayList<ItemStack>();
        for (RecipeOutput output : outputs) {
            if (output.isOutputting(rand)) {
                completeOutput.add(output.getOutput());
            }
        }
        return completeOutput.toArray(new ItemStack[completeOutput.size()]);
    }

    @Override
    public String toString() {
        return "Recipe: " + input.toString() + " >> " + outputs.toString();
    }
}
