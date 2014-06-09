package num.complexwiring.core.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraftforge.client.model.AdvancedModelLoader;
import num.complexwiring.client.temp.TechneModelLoader;
import num.complexwiring.client.RenderingHandler;
import num.complexwiring.machine.smasher.RenderSmasher;
import num.complexwiring.machine.smasher.TileSmasher;
import num.complexwiring.machine.storagebox.RenderStorageBox;
import num.complexwiring.machine.storagebox.TileStorageBox;

public class ClientProxy extends CommonProxy {
    @Override
    public void init() {
        super.init();
        AdvancedModelLoader.registerModelHandler(new TechneModelLoader());
        RenderingRegistry.registerBlockHandler(new RenderingHandler());
        ClientRegistry.bindTileEntitySpecialRenderer(TileStorageBox.class, new RenderStorageBox());
        ClientRegistry.bindTileEntitySpecialRenderer(TileSmasher.class, RenderSmasher.instance);
    }
}
