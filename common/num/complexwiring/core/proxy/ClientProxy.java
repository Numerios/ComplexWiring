package num.complexwiring.core.proxy;

import num.complexwiring.block.TileItemWire;
import num.complexwiring.render.WireModelRenderer;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy{
    @Override
    public void initRendering(){
      ClientRegistry.bindTileEntitySpecialRenderer(TileItemWire.class, new WireModelRenderer());
    }
}
