package num.complexwiring.block;

import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ModuleItem {
    public static Block blockItemWire;

    public static void init() {
        BlockItemWire blockItemWire = new BlockItemWire(499); // configs! :|
        GameRegistry.registerBlock(blockItemWire, "blockItemWire");
        GameRegistry.registerTileEntity(TileItemWire.class, "TileItemWire");
        LanguageRegistry.addName(blockItemWire, "blockItemWire!");
    }
}
