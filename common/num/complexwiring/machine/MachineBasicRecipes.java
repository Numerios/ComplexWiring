package num.complexwiring.machine;

import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class MachineBasicRecipes {
    private static HashMap<ItemStack, ItemStack> recipes = new HashMap<ItemStack, ItemStack>();

    public static void add(ItemStack input, ItemStack output) {
        recipes.put(input, output);
    }

    public static ItemStack getOutput(ItemStack is) {
        if (is != null) {
            for (Map.Entry entry : recipes.entrySet()) {
                if (((ItemStack) entry.getKey()).isItemEqual(is) && is.stackSize >= ((ItemStack) entry.getKey()).stackSize) {
                    return ((ItemStack) entry.getValue()).copy();
                }
            }
        }
        return null;
    }

    public static boolean contains(ItemStack input) {
        for (Map.Entry entry : recipes.entrySet()) {
            if (((ItemStack) entry.getKey()).isItemEqual(input)) {
                return true;
            }
        }
        return false;
    }
}
