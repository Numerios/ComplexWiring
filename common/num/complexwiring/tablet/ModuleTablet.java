package num.complexwiring.tablet;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import num.complexwiring.lib.Module;
import num.complexwiring.lib.Strings;
import num.complexwiring.tablet.guidebook.GuidebookPageRegistry;
import num.complexwiring.tablet.guidebook.ItemGuidebook;
import num.complexwiring.tablet.research.Research;
import num.complexwiring.tablet.research.ResearchManager;
import num.complexwiring.tablet.research.SolidResearchTask;
import num.complexwiring.tablet.startpaper.ItemStartPaper;
import num.complexwiring.tablet.tablet.ItemTablet;

public class ModuleTablet extends Module {
    public static Item itemTablet, itemStartPaper, itemGuidebook;

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
                new Research(Research.Tier.TIER_0, "First",
                        new SolidResearchTask("Stone", new ItemStack(Blocks.stone)),
                        new SolidResearchTask("Wood", new ItemStack(Blocks.log))
                ),
                new Research(Research.Tier.TIER_0, "Second",
                        new SolidResearchTask("Stone", new ItemStack(Blocks.stone)),
                        new SolidResearchTask("Wood", new ItemStack(Blocks.log))
                )
        );

        GuidebookPageRegistry.init();
    }

    private void registerBlocks() {

    }

    private void registerItems() {
        itemTablet = new ItemTablet();
        GameRegistry.registerItem(itemTablet, Strings.TABLET_NAME);

        itemStartPaper = new ItemStartPaper();
        GameRegistry.registerItem(itemStartPaper, Strings.STARTPAPER_NAME);

        itemGuidebook = new ItemGuidebook();
        GameRegistry.registerItem(itemGuidebook, Strings.GUIDEBOOK_NAME);
    }

    private void registerRecipes() {

    }
}
