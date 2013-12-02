package num.complexwiring.recipe;

import net.minecraft.item.ItemStack;
import num.complexwiring.core.Logger;

import java.util.Random;

public class RecipeOutput {

    private ItemStack output;
    private float chance;

    public RecipeOutput(ItemStack output, float chance) {
        this.output = output;
        this.chance = chance;
    }

    public boolean isOutputting(Random rand) {
        float f = rand.nextFloat();
        Logger.debug("RAND: " + output.toString() + " rand: " + f + " |chance: " + chance);
        return chance >= f;
    }

    public ItemStack getOutput() {
        return output;
    }

    public float getChance() {
        return chance;
    }

}
