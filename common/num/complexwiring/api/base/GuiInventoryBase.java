package num.complexwiring.api.base;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import num.complexwiring.machine.ContainerMachineOrealyzer;
import num.complexwiring.machine.TileEntityMachineOrealyzer;
import org.lwjgl.opengl.GL11;

public abstract class GuiInventoryBase extends GuiContainer {

    protected final InventoryPlayer playerInv;
    protected final ResourceLocation backgroundGUI;
    protected final TileEntityInventoryBase invTE;

    public GuiInventoryBase(InventoryPlayer player, TileEntityInventoryBase tile, ResourceLocation texture) {
        super(new ContainerMachineOrealyzer(player, (TileEntityMachineOrealyzer) tile)); //FIXME: mby some BaseContainer?
        playerInv = player;
        invTE = tile;
        backgroundGUI = texture;
    }


    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f(1F, 1F, 1F, 1F);
        mc.getTextureManager().bindTexture(backgroundGUI);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        super.drawGuiContainerForegroundLayer(par1, par2);

        String invName = invTE.getInvName();
        fontRenderer.drawString(invName, xSize / 2 - fontRenderer.getStringWidth(invName) / 2, 6, 0x404040);
    }
}
