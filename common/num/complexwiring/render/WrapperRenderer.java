package num.complexwiring.render;

import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import num.complexwiring.block.TileItemWire;
import num.complexwiring.core.Logger;
import num.complexwiring.core.Wrapper;

import org.lwjgl.opengl.GL11;

public class WrapperRenderer {
    public static RenderItem item;

    public static void render(TileItemWire wire, double x, double y, double z, Wrapper wrapper) {
        ItemStack is = wrapper.getContents();

        EntityItem itemEntity = new EntityItem(wire.worldObj);
        itemEntity.setEntityItemStack(is);

        item = new RenderItem() {
            public boolean shouldBob() {
                return false;
            }

            public boolean shouldSpreadItems() {
                return false;
            }
        };
        item.setRenderManager(RenderManager.instance);

        float scale = 0.7F;

        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_CULL_FACE);

        GL11.glPushMatrix();

        GL11.glTranslatef((float) x, (float) y, (float) z);
        GL11.glTranslatef(0, 0.10F, 0);
        offsetWrapperFromDirection(wrapper);
        GL11.glScalef(scale, scale, scale);
        item.doRenderItem(itemEntity, 0, 0, 0, 0, 0);

        GL11.glPopMatrix();

        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_LIGHTING);
    }

    private static void offsetWrapperFromDirection(Wrapper wrapper) {
        float x = 0F, y = 0F, z = 0F;
        switch (wrapper.getDirection()) {
            case UP: {
                x = 0.5F;
                y = wrapper.getProgress();
                z = 0.5F;
                break;
            }
            case DOWN: {
                x = 0.5F;
                y = 1F - wrapper.getProgress();
                z = 0.5F;
                break;
            }
            case WEST: {
                x = 1F - wrapper.getProgress();
                y = 0.5F;
                z = 0.5F;
                break;
            }
            case EAST: {
                x = wrapper.getProgress();
                y = 0.5F;
                z = 0.5F;
                break;
            }
            case NORTH: {
                x = 0.5F;
                y = 0.5F;
                z = 1F - wrapper.getProgress();
                break;
            }
            case SOUTH: {
                x = 0.5F;
                y = 0.5F;
                z = wrapper.getProgress();
                break;
            }
            default: {
                break;
            }
        }
        GL11.glTranslatef(x, y, z);
        return;
    }
}
