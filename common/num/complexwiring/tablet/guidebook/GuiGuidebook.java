package num.complexwiring.tablet.guidebook;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import num.complexwiring.client.gui.GuiHelper;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class GuiGuidebook extends GuiContainer {
    protected final ResourceLocation texture;
    protected Rectangle leftPage, rightPage;
    protected int page = 0;

    public GuiGuidebook(EntityPlayer player, int page) {
        super(new ContainerGuidebook(player.inventory));

        this.page = page;
        xSize = 216;
        ySize = 216;
        leftPage = new Rectangle(7, 7, 96, 118);
        rightPage = new Rectangle(113, 7, 96, 118);
        texture = new ResourceLocation("complexwiring", "textures/gui/tablet/guidebook/blank.png");
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        mc.getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int i, int j) {
        super.drawGuiContainerForegroundLayer(i, j);

        GL11.glPushMatrix();
        GL11.glTranslated(leftPage.getX(), leftPage.getY(), 0.0D);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        drawLeftPage();
        GL11.glPopMatrix();

        glTranslateDefault();

        GL11.glPushMatrix();
        GL11.glTranslated(rightPage.getX(), rightPage.getY(), 0.0D);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        drawRightPage();
        GL11.glPopMatrix();

        glTranslateDefault(); //TODO: BUTTONS FOR SWITCHING PAGES!
    }

    protected void drawLeftPage() {
        GuiHelper.drawCenteredString(fontRenderer, "Left Page", (int) leftPage.getWidth(), 0, 0x606060);
        GuiHelper.drawCenteredString(fontRenderer, "" + page, (int) leftPage.getWidth(), (int) leftPage.getHeight() - 6, 0x606060);
    }

    protected void drawRightPage() {
        GuiHelper.drawCenteredString(fontRenderer, "Right Page", (int) rightPage.getWidth(), 0, 0x606060);
        GuiHelper.drawCenteredString(fontRenderer, "" + (page + 1), (int) rightPage.getWidth(), (int) rightPage.getHeight() - 6, 0x606060);
    }

    private void glTranslateDefault() {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) guiLeft, (float) guiTop, 0.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();
    }
}
