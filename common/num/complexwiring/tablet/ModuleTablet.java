package num.complexwiring.tablet;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import num.complexwiring.lib.Module;
import num.complexwiring.lib.Strings;
import num.complexwiring.recipe.RecipeManager;
import num.complexwiring.tablet.research.Research;
import num.complexwiring.tablet.research.ResearchManager;
import num.complexwiring.tablet.research.SolidResearchTask;

public class ModuleTablet extends Module {
    public static Item itemTablet, itemStartPaper, itemGuideBook;

    @Override
    public void preInit() {
        registerBlocks();
        registerItems();
    }

    @Override
    public void init() {
        registerRecipes();
        ResearchManager.init();
        ResearchManager.addAll(
                new Research(Research.Tier.TIER_0, "Research", "Testing research",
                        new SolidResearchTask("Stone", "Get some stone", new ItemStack(Block.stone)),
                        new SolidResearchTask("Wood", "Get some wood", new ItemStack(Block.wood))
                ),
                new Research(Research.Tier.TIER_0, "Research2", "Testing again",
                    new SolidResearchTask("Stone", "Get some stone", new ItemStack(Block.stone)),
                    new SolidResearchTask("Wood", "Get some wood", new ItemStack(Block.wood))
                )
        );
    }

    private void registerBlocks() {

    }

    private void registerItems() {
        itemTablet = new ItemTablet(1024);
        GameRegistry.registerItem(itemTablet, Strings.TABLET_NAME);

        itemStartPaper = new ItemStartPaper(1022);
        GameRegistry.registerItem(itemStartPaper, Strings.STARTPAPER_NAME);

        itemGuideBook = new ItemGuideBook(1022);
        GameRegistry.registerItem(itemGuideBook, Strings.GUIDEBOOK_NAME);
    }

    private void registerRecipes() {

    }
}
