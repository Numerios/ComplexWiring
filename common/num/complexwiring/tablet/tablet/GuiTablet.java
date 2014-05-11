package num.complexwiring.tablet.tablet;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiTablet extends GuiScreen {
    protected final ResourceLocation texture = new ResourceLocation("complexwiring", "textures/gui/tablet/" + "basic" + ".png");
    protected final EntityPlayer player;

    protected int page = 0;
    protected int xSize = 216;
    protected int ySize = 166;

    protected int guiLeft;
    protected int guiTop;

    public GuiTablet(EntityPlayer player, int page) {
        this.player = player;
        this.page = page;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
    }

    @Override
    public void drawScreen(int par1, int par2, float par3) {
        drawBackground();
        super.drawScreen(par1, par2, par3);
        GL11.glPushMatrix();
        GL11.glTranslatef((float) guiLeft, (float) guiTop, 0.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        drawForeground();
        GL11.glPopMatrix();
    }

    protected void drawBackground() {
        this.mc.getTextureManager().bindTexture(texture);
        this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    protected void drawForeground() {
        fontRendererObj.drawString("§l The Tablet!", xSize / 2 - fontRendererObj.getStringWidth("§l The Tablet!") / 2, 6, 0x404040);

        fontRendererObj.drawString("Welcome to the magical", xSize / 2 - fontRendererObj.getStringWidth("Welcome to the magical") / 2, 44, 0x404040);
        fontRendererObj.drawString("world of technology,", xSize / 2 - fontRendererObj.getStringWidth("world of technology,") / 2, 52, 0x404040);

        fontRendererObj.drawString("§n" + player.getDisplayName(), xSize / 2 - fontRendererObj.getStringWidth("§n" + player.getDisplayName()) / 2, 68, 0x404040);

        fontRendererObj.drawString("§o Complex Wiring,", xSize / 2 - fontRendererObj.getStringWidth("§o Complex Wiring,") / 2, ySize - 24, 0x404040);
        fontRendererObj.drawString("§o we haven't even started yet ;)", xSize / 2 - fontRendererObj.getStringWidth("§o we haven't even started yet ;)") / 2, ySize - 14, 0x404040);
    }
}
