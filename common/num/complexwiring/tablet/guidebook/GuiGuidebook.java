package num.complexwiring.tablet.guidebook;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import num.complexwiring.api.research.IResearch;
import num.complexwiring.api.research.IResearchTask;
import num.complexwiring.api.tablet.GuidebookPage;
import num.complexwiring.client.gui.GuiHelper;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class GuiGuidebook extends GuiContainer {
    protected final ResourceLocation texture;
    protected Rectangle leftPageRect, rightPageRect;
    protected int page = 0;
    protected GuidebookPage guidebookPage;

    public GuiGuidebook(EntityPlayer player) {
        super(new ContainerGuidebook(player.inventory, ((ItemGuidebook) player.inventory.getCurrentItem().getItem()).getItemInventory(player.inventory.getCurrentItem())));

        xSize = 216;
        ySize = 216;
        leftPageRect = new Rectangle(7, 7, 96, 118);
        rightPageRect = new Rectangle(113, 7, 96, 118);
        texture = new ResourceLocation("complexwiring", "textures/gui/tablet/guidebook/blank.png");
    }

    @Override
    public void initGui() {
        super.initGui();
        buttonList.clear();
        buttonList.add(new GuiButton(0, guiLeft + 10 + 7, guiTop + 110, 20, 20, "<"));
        buttonList.add(new GuiButton(1, guiLeft + 10 + 177, guiTop + 110, 20, 20, ">"));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        mc.getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int i, int j) {
        super.drawGuiContainerForegroundLayer(i, j);
        boolean unicodeFlag = this.fontRenderer.getUnicodeFlag();
        this.fontRenderer.setUnicodeFlag(true);

        GL11.glPushMatrix();
        GL11.glTranslated(leftPageRect.getX(), leftPageRect.getY(), 0.0D);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        drawLeftPage();
        GL11.glPopMatrix();

        glTranslateDefault();

        GL11.glPushMatrix();
        GL11.glTranslated(rightPageRect.getX(), rightPageRect.getY(), 0.0D);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        drawRightPage();
        GL11.glPopMatrix();

        glTranslateDefault();

        this.fontRenderer.setUnicodeFlag(unicodeFlag);
    }

    //TODO: TEXT WITH MAX WIDTH (auto /n)
    protected void drawLeftPage() {
        GuiHelper.drawCenteredString(fontRenderer, "Left Page", (int) leftPageRect.getWidth(), 0, 0x606060);
        guidebookPage = GuidebookPageRegistry.pageMap.get(page);
        if (guidebookPage.type == GuidebookPage.Type.TEXT) {
            GuiHelper.drawLocaleCenteredString(fontRenderer, guidebookPage.text, (int) leftPageRect.getWidth(), 10, 0x606060);
        } else if (guidebookPage.type == GuidebookPage.Type.RESEARCH) {
            IResearch research = guidebookPage.research;
            GuiHelper.drawCenteredString(fontRenderer, "§l" + research.getLocalizedName(), (int) leftPageRect.getWidth(), 10, 0x606060);
            GuiHelper.drawCenteredString(fontRenderer, "§o" + research.getLocalizedDesc(), (int) leftPageRect.getWidth(), 18, 0x606060);
            int n = 0;
            for (IResearchTask task : research.getResearchTasks()) {
                GuiHelper.drawCenteredString(fontRenderer, "§8" + task.getLocalizedName(), (int) leftPageRect.getWidth(), 30 + (n * 25), 0x606060);
                GuiHelper.drawCenteredString(fontRenderer, "§7" + task.getLocalizedDesc(), (int) leftPageRect.getWidth(), 40 + (n * 25), 0x606060);
                n++;
            }
        }
        GuiHelper.drawCenteredString(fontRenderer, "" + page, (int) leftPageRect.getWidth(), (int) leftPageRect.getHeight() - 6, 0x606060);
    }

    protected void drawRightPage() {
        GuiHelper.drawCenteredString(fontRenderer, "Right Page", (int) rightPageRect.getWidth(), 0, 0x606060);
        guidebookPage = GuidebookPageRegistry.pageMap.get(page + 1);
        if (guidebookPage.type == GuidebookPage.Type.TEXT) {
            GuiHelper.drawLocaleCenteredString(fontRenderer, guidebookPage.text, (int) leftPageRect.getWidth(), 10, 0x606060);
        } else if (guidebookPage.type == GuidebookPage.Type.RESEARCH) {
            IResearch research = guidebookPage.research;
            GuiHelper.drawCenteredString(fontRenderer, "§l" + research.getLocalizedName(), (int) leftPageRect.getWidth(), 10, 0x606060);
            GuiHelper.drawCenteredString(fontRenderer, "§o" + research.getLocalizedDesc(), (int) leftPageRect.getWidth(), 18, 0x606060);
            int n = 0;
            for (IResearchTask task : research.getResearchTasks()) {
                GuiHelper.drawCenteredString(fontRenderer, "§8" + task.getLocalizedName(), (int) leftPageRect.getWidth(), 30 + (n * 25), 0x606060);
                GuiHelper.drawCenteredString(fontRenderer, "§7" + task.getLocalizedDesc(), (int) leftPageRect.getWidth(), 40 + (n * 25), 0x606060);
                n++;
            }
        }
        GuiHelper.drawCenteredString(fontRenderer, "" + (page + 1), (int) rightPageRect.getWidth(), (int) rightPageRect.getHeight() - 6, 0x606060);
    }

    private void glTranslateDefault() {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) guiLeft, (float) guiTop, 0.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();
    }

    public void actionPerformed(GuiButton button) {
        if (button.id == 0)   //backward
            if ((page - 2) >= 0) {
                page -= 2;
            }
        if (button.id == 1) { //forward
            if ((page + 2) < GuidebookPageRegistry.MAX_PAGE) {
                page += 2;
            }
        }
    }
}
