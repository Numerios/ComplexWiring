package num.complexwiring.api.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import num.complexwiring.api.vec.Vector3;
import num.complexwiring.recipe.RecipeManager;

import java.util.ArrayList;
import java.util.Random;

public class SmasherRecipe implements ICWRecipe {
    private ItemStack input;
    private ItemStack[] output;
    private int neededPower;

    public SmasherRecipe(Vector3 vec3, World world, int neededPower, ItemStack... output) {
        this(vec3.getIS(world), neededPower, output);
    }

    public SmasherRecipe(ItemStack input, int neededPower, ItemStack... output) {
        this.input = input;
        this.output = output;
        this.neededPower = neededPower;
    }

    public boolean matches(ItemStack is) {
        //TODO: Working OreDictionary support
        return input.isItemEqual(is);
    }

    public ItemStack getInput() {
        return input;
    }

    public int getNeededPower() {
        return neededPower;
    }

    public ArrayList<ItemStack> getOutput(Random rand) {
        ArrayList<ItemStack> completeOutput = new ArrayList<ItemStack>();
        for (ItemStack is : output) {
            completeOutput.add(is.copy());
        }
        return completeOutput;
    }

    @Override
    public RecipeManager.Type getType() {
        return RecipeManager.Type.SMASHER;
    }

    @Override
    public String toString() {
        return "Recipe: " + input.toString() + " >> " + output.toString();
    }
}
