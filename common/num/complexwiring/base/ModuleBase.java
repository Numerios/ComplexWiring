package num.complexwiring.base;

import net.minecraft.item.Item;
import num.complexwiring.core.CreativeTabCW;
import num.complexwiring.lib.Module;

public class ModuleBase extends Module {

    public static Item ingot, dust;
    public static CreativeTabCW tabCWBase = new CreativeTabCW("tabCWBase", null);

    @Override
    public void preInit() {
        registerItems();
    }

    @Override
    public void init() {
        registerRecipes();
    }

    private void registerItems() {
        ingot = new ItemIngot(1025);
        ((ItemIngot) ingot).registerOres();

        dust = new ItemDust(1026);
        ((ItemDust) dust).registerOres();
    }

    private void registerRecipes() {

    }
}
