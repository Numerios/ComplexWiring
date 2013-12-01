package num.complexwiring.recipe;

import net.minecraft.item.ItemStack;

import java.util.Random;

public class RecipeOutput {

    private ItemStack output;
    private float chance;

    public RecipeOutput(ItemStack output, float chance) {
        this.output = output;
        this.chance = chance;
    }

    public ItemStack outputs(Random rand) {
        if (rand.nextFloat() <= chance) return output;
        return null;
    }

    public ItemStack getOutput() {
        return output;
    }

    public float getChance() {
        return chance;
    }

}
