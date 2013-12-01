package num.complexwiring.machine;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiMachineBasic extends GuiContainer {
    public static final ResourceLocation texture = new ResourceLocation("complexwiring", "textures/gui/machine_basic.png");
    private TileEntityMachineBasic tile;
    private InventoryPlayer player;

    public GuiMachineBasic(InventoryPlayer player, TileEntityMachineBasic tile) {
        super(new ContainerMachineBasic(player, tile));
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
        fontRenderer.drawString(tile.getInvName(), this.xSize / 2 - this.fontRenderer.getStringWidth(tile.getInvName()) / 2, 6, 0x404040);
    }

    private void drawProgress(int burn, int cook, int cornerX, int cornerY) {
        if (burn != 0) {
            drawTexturedModalRect(cornerX + 56, cornerY + 36 + 12 - burn, 176, 12 - burn, 14, burn + 2);
        }
        drawTexturedModalRect(cornerX + 79, cornerY + 34, 176, 14, cook + 1, 16);
    }
}
