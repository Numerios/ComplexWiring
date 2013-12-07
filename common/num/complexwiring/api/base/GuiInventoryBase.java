package num.complexwiring.api.base;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import num.complexwiring.client.gui.GuiTooltip;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public abstract class GuiInventoryBase extends GuiContainer {

    protected final ContainerBase container;
    protected final ResourceLocation texture;
    protected List<GuiTooltip> tooltips;

    public GuiInventoryBase(ContainerBase container, ResourceLocation texture) {
        super(container);
        this.container = container;
        this.texture = texture;
        tooltips = new ArrayList<GuiTooltip>();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float f1) {
        super.drawScreen(mouseX, mouseY, f1);
        updateTooltips();
        drawTooltips(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f(1F, 1F, 1F, 1F);
        mc.getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        super.drawGuiContainerForegroundLayer(par1, par2);

        String invName = container.getTile().getInvName();
        fontRenderer.drawString(invName, xSize / 2 - fontRenderer.getStringWidth(invName) / 2, 6, 0x404040);
    }

    protected void updateTooltips(){

    }

    protected void drawTooltips(int mouseX, int mouseY) {
        for (GuiTooltip tooltip : tooltips) {
            if(tooltip.isHover(mouseX - guiLeft, mouseY - guiTop)){
                drawHoveringText(tooltip.getTexts(), mouseX, mouseY, fontRenderer);
            }
        }
    }

}
