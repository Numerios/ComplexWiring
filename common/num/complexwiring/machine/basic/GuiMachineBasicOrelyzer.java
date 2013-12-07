package num.complexwiring.machine.basic;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import num.complexwiring.ComplexWiring;
import num.complexwiring.api.base.GuiInventoryBase;

public class GuiMachineBasicOrelyzer extends GuiInventoryBase {

    private TileEntityBasicOrelyzer tile;

    public GuiMachineBasicOrelyzer(InventoryPlayer player, TileEntityBasicOrelyzer tile) {
        super(new ContainerMachineBasicOrelyzer(player, tile), new ResourceLocation("complexwiring", "textures/gui/machine/basic/" + EnumBasicMachine.ORELYZER.getUnlocalizedName() + ".png"));
        this.tile = tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        super.drawGuiContainerBackgroundLayer(f, i, j);
        drawProgress(tile.getBurnTimeScaled(12), tile.getProcessedTimeScaled(24), guiLeft, guiTop);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int i, int j) {
        super.drawGuiContainerForegroundLayer(i, j);

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
