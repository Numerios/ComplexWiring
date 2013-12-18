package num.complexwiring.tablet.research;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import num.complexwiring.api.research.IResearch;
import num.complexwiring.api.research.IResearchTask;
import num.complexwiring.tablet.ContainerGuideBook;

import java.util.ArrayList;

public class GuiGuideBook extends GuiContainer {

    protected final ResourceLocation texture;

    public GuiGuideBook(EntityPlayer player) {
        super(new ContainerGuideBook(player.inventory));

        xSize = 216;
        ySize = 216;
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

        int line = 0;
        for(IResearch research : ResearchManager.researchList){
            fontRenderer.drawString(research.getLocalizedName(), 15, 15 + line * 10, 0x404040);
            ArrayList<IResearchTask> tasks = research.getResearchTasks();
            line++;
            for (IResearchTask task : tasks){
                fontRenderer.drawString(task.getLocalizedName(), 20, 15 + line * 10, 0x606060);
                line++;
            }
            line++;
        }
    }
}
