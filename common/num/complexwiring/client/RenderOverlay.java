package num.complexwiring.client;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderOverlay {
    private static RenderItem renderItem = ((RenderItem) RenderManager.instance.getEntityClassRenderObject(EntityItem.class));

    public static void renderItemOnSide(ItemStack is, double x, double y, double z, ForgeDirection side) {
        if (is != null) {
            GL11.glPushMatrix();
            switch (side) {
                case NORTH:
                    GL11.glTranslated(x + 0.755, y + 0.755, z - 0.001);
                    break;
                case SOUTH:
                    GL11.glTranslated(x + 0.245, y + 0.755, z + 1.001);
                    GL11.glRotatef(180, 0, 1, 0);
                    break;
                case WEST:
                    GL11.glTranslated(x - 0.001, y + 0.755, z + 0.245);
                    GL11.glRotatef(90, 0, 1, 0);
                    break;
                case EAST:
                    GL11.glTranslated(x + 1.001, y + 0.755, z + 0.755);
                    GL11.glRotatef(-90, 0, 1, 0);
                    break;
            }
            GL11.glScalef(0.031875F, 0.031875F, -0.00001F);    // 51/1600  = 1.02 * 1/32
            GL11.glRotatef(180, 0, 0, 1);
            TextureManager renderEngine = Minecraft.getMinecraft().renderEngine;
            GL11.glDisable(GL11.GL_LIGHTING);
            renderItem.renderItemIntoGUI(Minecraft.getMinecraft().fontRenderer, renderEngine, is, 0, 0);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPopMatrix();
        }
    }

    public static void renderDynamicTextOnSide(String string, ForgeDirection side, float maxScale, double x, double y, double z) {
        GL11.glPushMatrix();

        GL11.glPolygonOffset(-10, -10);
        GL11.glEnable(GL11.GL_POLYGON_OFFSET_FILL);

        float displayWidth = 1 - (2 / 16);
        float displayHeight = 1 - (2 / 16);
        GL11.glTranslated(x, y, z);
        GL11.glPushMatrix();

        rotate(side);

        GL11.glTranslatef(displayWidth / 2, 1F, displayHeight / 2);
        GL11.glRotatef(-90, 1, 0, 0);

        FontRenderer fontRenderer = FMLClientHandler.instance().getClient().fontRenderer;

        int requiredWidth = Math.max(fontRenderer.getStringWidth(string), 1);
        int requiredHeight = fontRenderer.FONT_HEIGHT + 2;

        float scaleX = (displayWidth / requiredWidth);
        float scaleY = (displayHeight / requiredHeight);
        float scale = Math.min(maxScale, Math.min(scaleX, scaleY) * 0.8F);

        GL11.glScalef(scale, -scale, scale);
        GL11.glDepthMask(false);

        int realHeight = (int) Math.floor(displayHeight / scale);
        int realWidth = (int) Math.floor(displayWidth / scale);

        int offsetX = (realWidth - requiredWidth) / 2;
        int offsetY = (realHeight - requiredHeight) / 2;

        GL11.glDisable(GL11.GL_LIGHTING);
        fontRenderer.drawString("§F" + string, offsetX - (realWidth / 2), 1 + offsetY - (realHeight / 2), 1);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_POLYGON_OFFSET_FILL);

        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    public static void renderScaledTextOnSide(String string, ForgeDirection side, float scale, double x, double y, double z) {
        GL11.glPushMatrix();

        GL11.glPolygonOffset(-10, -10);
        GL11.glEnable(GL11.GL_POLYGON_OFFSET_FILL);

        float displayWidth = 1 - (2 / 16);
        float displayHeight = 1 - (2 / 16);
        GL11.glTranslated(x, y, z);
        GL11.glPushMatrix();

        rotate(side);

        GL11.glTranslatef(displayWidth / 2, 1F, displayHeight / 2);
        GL11.glRotatef(-90, 1, 0, 0);

        FontRenderer fontRenderer = FMLClientHandler.instance().getClient().fontRenderer;

        int requiredWidth = Math.max(fontRenderer.getStringWidth(string), 1);
        int requiredHeight = fontRenderer.FONT_HEIGHT + 2;

        GL11.glScalef(scale, -scale, scale);
        GL11.glDepthMask(false);

        int realHeight = (int) Math.floor(displayHeight / scale);
        int realWidth = (int) Math.floor(displayWidth / scale);

        int offsetX = (realWidth - requiredWidth) / 2;
        int offsetY = (realHeight - requiredHeight) / 2;

        GL11.glDisable(GL11.GL_LIGHTING);
        fontRenderer.drawString("§F" + string, offsetX - (realWidth / 2), 1 + offsetY - (realHeight / 2), 1);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_POLYGON_OFFSET_FILL);

        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    public static void rotate(ForgeDirection side) {
        switch (side) {
            case SOUTH:
                GL11.glTranslatef(0, 1, 0);
                GL11.glRotatef(0, 0, 1, 0);
                GL11.glRotatef(90, 1, 0, 0);
                break;
            case NORTH:
                GL11.glTranslatef(1, 1, 1);
                GL11.glRotatef(180, 0, 1, 0);
                GL11.glRotatef(90, 1, 0, 0);
                break;
            case EAST:
                GL11.glTranslatef(0, 1, 1);
                GL11.glRotatef(90, 0, 1, 0);
                GL11.glRotatef(90, 1, 0, 0);
                break;
            case WEST:
                GL11.glTranslatef(1, 1, 0);
                GL11.glRotatef(-90, 0, 1, 0);
                GL11.glRotatef(90, 1, 0, 0);
                break;
        }
    }

    public static void renderISWithTextOnSide(TileEntity tile, ItemStack is, double x, double y, double z, ForgeDirection side) {
        String amount = "0";
        String name = "";
        if (is != null) {
            amount = (is.stackSize % 64) + "";
            int stacks = (is.stackSize / 64);
            if(stacks > 0) amount = stacks + "x64 + " + amount;

            name = is.getDisplayName();
        }

        renderItemOnSide(is, x, y, z, side);
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);
        renderDynamicTextOnSide(name, side, 0.0175F, x, y - 0.425F, z);
        renderScaledTextOnSide(amount, side, 0.01F, x, y + 0.425F, z);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}
