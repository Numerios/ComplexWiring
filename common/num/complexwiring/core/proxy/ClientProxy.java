package num.complexwiring.core.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import num.complexwiring.block.TileItemWire;
import num.complexwiring.render.WireModelRenderer;

public class ClientProxy extends CommonProxy {
    @Override
    public void initRendering() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileItemWire.class, new WireModelRenderer());
    }
}
