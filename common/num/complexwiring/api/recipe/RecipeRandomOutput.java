package num.complexwiring.api.recipe;

import net.minecraft.item.ItemStack;

import java.util.Random;

/**
 * A base class for randomized output
 */
public class RecipeRandomOutput {

    private ItemStack output;
    private float chance;

    public RecipeRandomOutput(ItemStack output) {
        this.output = output;
        this.chance = 1F;
    }

    public RecipeRandomOutput(ItemStack output, float chance) {
        this.output = output;
        this.chance = chance;
    }

    public boolean doesOutput(Random rand) {
        float f = rand.nextFloat();
        return chance >= f;
    }

    public ItemStack getOutput() {
        return output;
    }

    public float getChance() {
        return chance;
    }

}
