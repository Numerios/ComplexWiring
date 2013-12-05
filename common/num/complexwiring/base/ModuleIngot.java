package num.complexwiring.base;

import num.complexwiring.lib.Module;

public class ModuleIngot extends Module{

    public static ItemIngot itemIngot;

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
        itemIngot.registerOres();
    }

    private void registerRecipes() {

    }
}
