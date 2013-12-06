package num.complexwiring.machine.powered;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import num.complexwiring.ComplexWiring;
import num.complexwiring.machine.basic.EnumBasicMachine;
import org.lwjgl.opengl.GL11;

public class GuiMachinePoweredOrelyzer extends GuiContainer{
    //FIXME: EXTEND ME ON GUIINVBASE
    protected InventoryPlayer playerInv;
    protected final ResourceLocation texture = new ResourceLocation("complexwiring", "textures/gui/" + EnumBasicMachine.ORELYZER.getUnlocalizedName() + ".png");
    protected TileEntityPoweredOrelyzer tile;

    public GuiMachinePoweredOrelyzer(InventoryPlayer player, TileEntityPoweredOrelyzer tile) {
        super(new ContainerMachinePoweredOrelyzer(player, tile));
        playerInv = player;
        this.tile = tile;
    }


    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f(1F, 1F, 1F, 1F);
        mc.getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        drawProgress(tile.getEnergyScaled(12), tile.getProcessedTimeScaled(24), guiLeft, guiTop);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        super.drawGuiContainerForegroundLayer(par1, par2);

        String invName = tile.getInvName();
        fontRenderer.drawString(invName, xSize / 2 - fontRenderer.getStringWidth(invName) / 2, 6, 0x404040);


        if (ComplexWiring.DEBUG) {
            fontRenderer.drawString("BURN:", 6, 20, 0x666666);
            fontRenderer.drawString(" " + tile.getStoredEnergy(), 6 + fontRenderer.getStringWidth("BURN:"), 20, 0x333333);
            fontRenderer.drawString(" " + tile.getEnergyScaled(100) + "%", 6 + fontRenderer.getStringWidth("BURN:"), 35, 0x333333);
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
