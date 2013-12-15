package num.complexwiring.tablet;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiStartPaper extends GuiScreen {
    protected final ResourceLocation texture = new ResourceLocation("complexwiring", "textures/gui/tablet/startpaper/" + "crafting" + ".png");
    protected final EntityPlayer player;
    protected int xSize = 132;
    protected int ySize = 166;

    protected int guiLeft;
    protected int guiTop;

    private int craftingGridX = 42;
    private int craftingGridY = 80;

    public GuiStartPaper(EntityPlayer player) {
        this.player = player;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
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
        fontRenderer.drawString("§lSURIVIVE", xSize / 2 - fontRenderer.getStringWidth("§lSURIVIVE") / 2, 16, 0x332211);
        fontRenderer.drawString("§lMINE", xSize / 2 - fontRenderer.getStringWidth("§lMINE") / 2, 28, 0x332211);
        fontRenderer.drawString("§lCRAFT", xSize / 2 - fontRenderer.getStringWidth("§lCRAFT") / 2, 40, 0x332211);

        fontRenderer.drawString("§n" + player.getDisplayName(), xSize / 2 - fontRenderer.getStringWidth("§n" + player.getDisplayName()) / 2, 60, 0x443322);

        fontRenderer.drawString("§oComplex Wiring", xSize / 2 - fontRenderer.getStringWidth("§oComplex Wiring") / 2, ySize - 28, 0x554433);

        RenderItem renderItem = new RenderItem();
        renderItem.renderItemIntoGUI(fontRenderer, Minecraft.getMinecraft().getTextureManager(), new ItemStack(Item.redstone), craftingGridX + 16, craftingGridY);
        renderItem.renderItemIntoGUI(fontRenderer, Minecraft.getMinecraft().getTextureManager(), new ItemStack(Item.paper), craftingGridX, craftingGridY + 16);
        renderItem.renderItemIntoGUI(fontRenderer, Minecraft.getMinecraft().getTextureManager(), new ItemStack(Item.book), craftingGridX + 16, craftingGridY + 16);
        renderItem.renderItemIntoGUI(fontRenderer, Minecraft.getMinecraft().getTextureManager(), new ItemStack(Item.paper), craftingGridX + 32, craftingGridY + 16);
        renderItem.renderItemIntoGUI(fontRenderer, Minecraft.getMinecraft().getTextureManager(), new ItemStack(Item.redstone), craftingGridX + 16, craftingGridY + 32);
    }
}
