package num.complexwiring.machine.basic;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import num.complexwiring.api.prefab.GuiInventoryBase;
import num.complexwiring.client.gui.GuiTooltip;

import java.awt.*;

public class GuiMachineBasicCrusher extends GuiInventoryBase {
    protected GuiTooltip fuelTooltip, progressTooltip;
    protected TileEntityBasicCrusher tile;

    public GuiMachineBasicCrusher(InventoryPlayer player, TileEntityBasicCrusher tile) {
        super(new ContainerMachineBasicCrusher(player, tile), new ResourceLocation("complexwiring", "textures/gui/machine/basic/" + EnumBasicMachine.ORELYZER.getUnlocalizedName() + ".png"));
        this.tile = tile;
    }

    @Override
    protected void initTooltips() {
        fuelTooltip = new GuiTooltip(new Rectangle(60, 35, 8, 16));
        fuelTooltip.addText("{Fuel remaining in ticks}");
        progressTooltip = new GuiTooltip(new Rectangle(78, 34, 26, 17));
        progressTooltip.addText("{Progress in %}");

        addTooltip(fuelTooltip, progressTooltip);
    }

    @Override
    protected void updateTooltips() {
        fuelTooltip.setText(0, tile.burnTime + " ticks left");
        progressTooltip.setText(0, tile.getProcessedTimeScaled(100) + "%");
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        super.drawGuiContainerBackgroundLayer(f, i, j);
        drawProgress(tile.getBurnTimeScaled(12), tile.getProcessedTimeScaled(24), guiLeft, guiTop);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int i, int j) {
        super.drawGuiContainerForegroundLayer(i, j);
    }

    private void drawProgress(int burn, int cook, int cornerX, int cornerY) {
        if (burn != 0) {
            drawTexturedModalRect(cornerX + 56, cornerY + 36 + 12 - burn, 176, 12 - burn, 14, burn + 2);
        }
        drawTexturedModalRect(cornerX + 79, cornerY + 34, 176, 14, cook + 1, 16);
    }
}
