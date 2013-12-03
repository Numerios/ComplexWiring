package num.complexwiring.recipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.Random;

public class Recipe {

    private final String oreDictionary;
    private ItemStack input;
    private RecipeOutput[] outputs;

    public Recipe(ItemStack input, RecipeOutput... outputs) {
        this.input = input;
        this.outputs = outputs;

        oreDictionary = OreDictionary.getOreName(input.itemID);
    }

    public boolean matches(ItemStack is) {
        return (input.isItemEqual(is) || OreDictionary.getOreName(is.itemID) == oreDictionary);
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

    public ArrayList<ItemStack> getCompleteOutput(Random rand) {
        ArrayList<ItemStack> completeOutput = new ArrayList<ItemStack>();
        for (RecipeOutput output : outputs) {
            float f = rand.nextFloat();
            if (output.getChance() >= f){
                completeOutput.add(output.getOutput().copy());
            }
        }
        return completeOutput;
    }

    @Override
    public String toString() {
        return "Recipe: " + input.toString() + " >> " + outputs.toString();
    }
}
