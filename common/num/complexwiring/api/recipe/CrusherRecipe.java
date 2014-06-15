package num.complexwiring.api.recipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import num.complexwiring.recipe.RecipeManager;

import java.util.ArrayList;
import java.util.Random;

public class CrusherRecipe implements ICWRecipe {
    private ItemStack input;
    private RecipeRandomOutput[] outputs;
    private int neededPower;

    private int[] oreIds;

    public CrusherRecipe(ItemStack input, int neededPower, RecipeRandomOutput... outputs) {
        this.input = input;
        this.outputs = outputs;
        this.neededPower = neededPower;

        oreIds = OreDictionary.getOreIDs(input);
    }

    public boolean matches(ItemStack is) {
        if (is == null || is.getItem() == null || input == null) return false;
        if (input.isItemEqual(is)) return true;

        int[] matchedOres = OreDictionary.getOreIDs(is);
        for (int matchedOre : matchedOres) {
            for (int recipeOre : oreIds) {
                if (recipeOre == matchedOre) return true;
            }
        }
        return false;
    }

    public ItemStack getInput() {
        return input;
    }

    public int getNeededPower() {
        return neededPower;
    }

    public ArrayList<ItemStack> getOutput(Random rand) {
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
    public RecipeManager.Type getType() {
        return RecipeManager.Type.CRUSHER;
    }

    @Override
    public String toString() {
        return "Recipe: " + input.toString() + " >> " + outputs.toString();
    }
}
