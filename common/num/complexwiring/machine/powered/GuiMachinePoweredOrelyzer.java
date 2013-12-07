package num.complexwiring.machine.powered;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import num.complexwiring.ComplexWiring;
import num.complexwiring.api.base.GuiInventoryBase;
import num.complexwiring.client.gui.GuiTooltip;

import java.awt.*;

public class GuiMachinePoweredOrelyzer extends GuiInventoryBase {
    protected GuiTooltip energyTooltip;
    protected TileEntityPoweredOrelyzer tile;

    public GuiMachinePoweredOrelyzer(InventoryPlayer player, TileEntityPoweredOrelyzer tile) {
        super(new ContainerMachinePoweredOrelyzer(player, tile), new ResourceLocation("complexwiring", "textures/gui/machine/powered/" + EnumPoweredMachine.ORELYZER.getUnlocalizedName() + ".png"));
        this.tile = tile;

        energyTooltip = new GuiTooltip(new Rectangle(12, 11, 15, 40));
        energyTooltip.addText("{Stored energy}");
        tooltips.add(energyTooltip);
    }

    @Override
    protected void updateTooltips() {
        energyTooltip.setText(0, tile.getStoredEnergy() + " MJ");
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        super.drawGuiContainerBackgroundLayer(f, i, j);
        drawProgress(tile.getEnergyScaled(40), tile.getProcessedTimeScaled(24), guiLeft, guiTop);
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

    private void drawProgress(int energy, int cook, int cornerX, int cornerY) {
      /*  if(tile.isPowered()){
            drawTexturedModalRect(cornerX + 58, cornerY + 46 + 12, 176, 13, 14, 14);
        }*/
        if (energy != 0) {  //x, y, u, v, w, h
            // drawTexturedModalRect(cornerX + 56, cornerY + 36 + 12 - energy, 176, 12 - energy, 14, energy + 2);
            drawTexturedModalRect(cornerX + 11, cornerY + 12 + 42 - 2 - energy, 176, 31 + 42 - energy, 18, energy);
        }
        drawTexturedModalRect(cornerX + 79, cornerY + 34, 176, 14, cook + 1, 16);
    }
}
