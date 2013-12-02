package num.complexwiring.api.base;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import num.complexwiring.machine.ContainerOrelyzer;
import num.complexwiring.machine.TileEntityOrelyzer;
import org.lwjgl.opengl.GL11;

public abstract class GuiInventoryBase extends GuiContainer {

    protected InventoryPlayer playerInv;
    protected final ResourceLocation texture;
    protected TileEntityInventoryBase tile;

    public GuiInventoryBase(InventoryPlayer player, TileEntityInventoryBase tile, ResourceLocation texture) {
        super(new ContainerOrelyzer(player, (TileEntityOrelyzer) tile)); //TODO: mby some BaseContainer?
        playerInv = player;
        this.tile = tile;
        this.texture = texture;
    }


    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f(1F, 1F, 1F, 1F);
        mc.getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        super.drawGuiContainerForegroundLayer(par1, par2);

        String invName = tile.getInvName();
        fontRenderer.drawString(invName, xSize / 2 - fontRenderer.getStringWidth(invName) / 2, 6, 0x404040);
    }
}
