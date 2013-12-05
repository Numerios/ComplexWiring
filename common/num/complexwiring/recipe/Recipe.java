package num.complexwiring.recipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import num.complexwiring.core.Logger;

import java.util.ArrayList;
import java.util.Random;

public class Recipe {

    private final String oreDictionary;
    private ItemStack input;
    private RecipeOutput[] outputs;
    private int neededPower;

    public Recipe(ItemStack input, int neededPower, RecipeOutput... outputs) {
        this.input = input;
        this.outputs = outputs;
        this.neededPower = neededPower;

        oreDictionary = OreDictionary.getOreName(input.itemID);
    }

    public boolean matches(ItemStack is) {
        return (input.isItemEqual(is) || OreDictionary.getOreName(is.itemID) == oreDictionary);
    }

    public ItemStack getInput() {
        return input;
    }

    public int getNeededPower(){
        Logger.debug("NEEDED POWER: " + neededPower);
        return neededPower;
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
