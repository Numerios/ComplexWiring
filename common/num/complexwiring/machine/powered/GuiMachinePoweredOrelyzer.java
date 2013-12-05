package num.complexwiring.machine.powered;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import num.complexwiring.api.base.TileEntityInventoryBase;
import num.complexwiring.machine.basic.EnumBasicMachine;
import org.lwjgl.opengl.GL11;

public class GuiMachinePoweredOrelyzer extends GuiContainer{
    //FIXME: EXTEND ME ON GUIINVBASE
    protected InventoryPlayer playerInv;
    protected final ResourceLocation texture = new ResourceLocation("complexwiring", "textures/gui/" + EnumBasicMachine.ORELYZER.getUnlocalizedName() + ".png");
    protected TileEntityInventoryBase tile;

    public GuiMachinePoweredOrelyzer(InventoryPlayer player, TileEntityInventoryBase tile) {
        super(new ContainerMachinePoweredOrelyzer(player, (TileEntityPoweredOrelyzer) tile));
        playerInv = player;
        this.tile = tile;
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
