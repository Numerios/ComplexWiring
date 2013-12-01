package num.complexwiring.machine;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import num.complexwiring.ComplexWiring;
import org.lwjgl.opengl.GL11;

public class GuiMachineOrealyzer extends GuiContainer {
    public static final ResourceLocation texture = new ResourceLocation("complexwiring", "textures/gui/machine_basic.png");
    private TileEntityMachineOrealyzer tile;
    private InventoryPlayer player;

    public GuiMachineOrealyzer(InventoryPlayer player, TileEntityMachineOrealyzer tile) {
        super(new ContainerMachineOrealyzer(player, tile));
        this.tile = tile;
        this.player = player;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f(1F, 1F, 1F, 1F);
        mc.getTextureManager().bindTexture(texture);
        int cornerX = (width - xSize) / 2;
        int cornerY = (height - ySize) / 2;
        drawTexturedModalRect(cornerX, cornerY, 0, 0, xSize, ySize);

        drawProgress(tile.getBurnTimeScaled(12), tile.getProcessedTimeScaled(24), cornerX, cornerY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int i, int j) {
        super.drawGuiContainerForegroundLayer(i, j);
        fontRenderer.drawString(tile.getInvName(), this.xSize / 2 - fontRenderer.getStringWidth(tile.getInvName()) / 2, 6, 0x404040);

        if (ComplexWiring.DEBUG) {
            fontRenderer.drawString("BURN:", 6, 20, 0x666666);
            fontRenderer.drawString(" " + tile.machineBurnTime, 6 + fontRenderer.getStringWidth("BURN:"), 20, 0x333333);
            fontRenderer.drawString(" " + tile.getBurnTimeScaled(100) + "%", 6 + fontRenderer.getStringWidth("BURN:"), 35, 0x333333);
            fontRenderer.drawString("COOK: ", 6, 55, 0x666666);
            fontRenderer.drawString(" " + tile.machineProcessTime, 6 + fontRenderer.getStringWidth("COOK:"), 55, 0x333333);
            fontRenderer.drawString(" " + tile.getProcessedTimeScaled(100) + "%", 6 + fontRenderer.getStringWidth("COOK:"), 70, 0x333333);

        }

    }

    private void drawProgress(int burn, int cook, int cornerX, int cornerY) {
        if (burn != 0) {
            drawTexturedModalRect(cornerX + 56, cornerY + 36 + 12 - burn, 176, 12 - burn, 14, burn + 2);
        }
        drawTexturedModalRect(cornerX + 79, cornerY + 34, 176, 14, cook + 1, 16);
    }
}
