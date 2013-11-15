package num.complexwiring.block;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import num.complexwiring.item.ItemComplexTool;

public class ModuleItem {
    public static Block blockItemWire;

    public static void init() {
        BlockItemWire blockItemWire = new BlockItemWire(499); // configs! :|
        GameRegistry.registerBlock(blockItemWire, "blockItemWire");
        GameRegistry.registerTileEntity(TileItemWire.class, "TileItemWire");
        LanguageRegistry.addName(blockItemWire, "blockItemWire!");

        ItemComplexTool itemComplexTool = new ItemComplexTool(4999);
        GameRegistry.registerItem(itemComplexTool, "itemComplexTool");
        LanguageRegistry.addName(itemComplexTool, "The Complex Tool!");

        BlockItemHub blockItemHub = new BlockItemHub(500); //TODO: ID Configs, don't you think?
        GameRegistry.registerBlock(blockItemHub, "blockItemHub");
        GameRegistry.registerTileEntity(TileItemHub.class, "TileItemHub");
        LanguageRegistry.addName(blockItemHub, "blockItemHub!");
    }
}
