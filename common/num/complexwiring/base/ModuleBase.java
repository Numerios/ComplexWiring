package num.complexwiring.base;

import net.minecraft.item.Item;
import num.complexwiring.lib.Module;

public class ModuleBase extends Module {

    public static Item itemIngot;

    @Override
    public void preInit() {
        registerItems();
    }

    @Override
    public void init() {
        registerRecipes();
    }

    private void registerItems() {
        itemIngot = new ItemIngot(1025);
        ((ItemIngot) itemIngot).registerOres();
    }

    private void registerRecipes() {

    }
}
