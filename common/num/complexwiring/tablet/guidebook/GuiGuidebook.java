package num.complexwiring.tablet.guidebook;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiGuidebook extends GuiContainer {
    protected final ResourceLocation texture = new ResourceLocation("complexwiring", "textures/gui/tablet/guidebook/blank.png");

    public GuiGuidebook(EntityPlayer player) {
        super(new ContainerGuidebook(player.inventory));

        xSize = 216;
        ySize = 216;
    }

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        mc.getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int i, int j) {
        super.drawGuiContainerForegroundLayer(i, j);
        boolean unicodeFlag = this.fontRendererObj.getUnicodeFlag();
        this.fontRendererObj.setUnicodeFlag(true);
        this.fontRendererObj.setUnicodeFlag(unicodeFlag);
    }
}
