package num.complexwiring.tablet;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import num.complexwiring.lib.Module;
import num.complexwiring.lib.Strings;

public class ModuleTablet extends Module {
    public static Item itemTablet, itemStartPaper;

    @Override
    public void preInit() {
        registerBlocks();
        registerItems();
    }

    @Override
    public void init() {
        registerRecipes();
    }

    private void registerBlocks() {

    }

    private void registerItems() {
        ItemTablet itemTablet = new ItemTablet(1024);
        GameRegistry.registerItem(itemTablet, Strings.TABLET_NAME);

        ItemStartPaper itemStartPaper = new ItemStartPaper(1022);
        GameRegistry.registerItem(itemStartPaper, Strings.STARTPAPER_NAME);
    }

    private void registerRecipes() {

    }
}
