package num.complexwiring.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;

@SideOnly(Side.CLIENT)
public class CWRenderer implements IItemRenderer {
    public static final CWRenderer INSTANCE = new CWRenderer();
    private static final HashMap<Item, IOBJRenderer> RENDERER = new HashMap<Item, IOBJRenderer>();

    public static void register(Item item, IOBJRenderer renderer){
        MinecraftForgeClient.registerItemRenderer(item, INSTANCE);
        RENDERER.put(item, renderer);
    }

    @Override
    public boolean handleRenderType(ItemStack is, ItemRenderType type) {
        return RENDERER.containsKey(is);
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack is, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack is, Object... data) {
        GL11.glPushMatrix();
        RENDERER.get(is).renderInvItem(is);
    }
}
