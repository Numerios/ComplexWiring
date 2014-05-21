package num.complexwiring.core.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import num.complexwiring.client.RenderingHandler;
import num.complexwiring.machine.storagebox.RenderStorageBox;
import num.complexwiring.machine.storagebox.TileStorageBox;

public class ClientProxy extends CommonProxy {
    @Override
    public void init() {
        super.init();
        RenderingRegistry.registerBlockHandler(new RenderingHandler());
        ClientRegistry.bindTileEntitySpecialRenderer(TileStorageBox.class, new RenderStorageBox());
    }
}
