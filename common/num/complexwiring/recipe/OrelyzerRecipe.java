package num.complexwiring.recipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import num.complexwiring.api.recipe.RecipeRandomOutput;

import java.util.ArrayList;
import java.util.Random;

public class OrelyzerRecipe implements ICWRecipe {

    private final String oreDictionary;
    private ItemStack input;
    private RecipeRandomOutput[] outputs;
    private int neededPower;

    public OrelyzerRecipe(ItemStack input, int neededPower, RecipeRandomOutput... outputs) {
        this.input = input;
        this.outputs = outputs;
        this.neededPower = neededPower;

        oreDictionary = OreDictionary.getOreName(input.itemID);
    }

    public boolean matches(ItemStack is) {
        return (input.isItemEqual(is) || OreDictionary.getOreName(is.itemID).equals(oreDictionary));
    }

    public ItemStack getInput() {
        return input;
    }

    public int getNeededPower() {
        return neededPower;
    }

    public RecipeRandomOutput[] getOutputs() {
        return outputs;
    }

    public ArrayList<ItemStack> getCompleteOutput(Random rand) {
        ArrayList<ItemStack> completeOutput = new ArrayList<ItemStack>();
        for (RecipeRandomOutput output : outputs) {
            float f = rand.nextFloat();
            if (output.getChance() >= f) {
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
