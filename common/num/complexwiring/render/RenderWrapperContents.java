package num.complexwiring.render;

import net.minecraft.client.renderer.entity.RenderItem;

public class RenderWrapperContents extends RenderItem {
    @Override
    public boolean shouldBob() {
        return false;
    }

    @Override
    public boolean shouldSpreadItems() {
        return false;
    }
}
