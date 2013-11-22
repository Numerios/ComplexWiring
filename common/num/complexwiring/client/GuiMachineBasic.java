package num.complexwiring.client;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import num.complexwiring.machine.ContainerMachineBasic;
import num.complexwiring.machine.TileEntityMachineBasic;
import org.lwjgl.opengl.GL11;

public class GuiMachineBasic extends GuiContainer {
    public static final ResourceLocation texture = new ResourceLocation("complexwiring", "textures/gui/machine_basic.png");
    protected final TileEntityMachineBasic tile;
    protected final InventoryPlayer player;

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
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int i, int j){
        super.drawGuiContainerForegroundLayer(i, j);
        //CODE FROM HOPPERS
        this.fontRenderer.drawString(tile.isInvNameLocalized() ? tile.getInvName() : I18n.getString(tile.getInvName()), 8, 6, 4210752);
        this.fontRenderer.drawString(player.isInvNameLocalized() ? player.getInvName() : I18n.getString(player.getInvName()), 8, this.ySize - 96 + 2, 4210752);    }
}
