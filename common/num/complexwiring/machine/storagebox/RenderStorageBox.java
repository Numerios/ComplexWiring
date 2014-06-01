package num.complexwiring.machine.storagebox;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import num.complexwiring.client.RenderOverlay;
import num.complexwiring.core.Logger;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderStorageBox extends TileEntitySpecialRenderer {

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {
        if (tile instanceof TileStorageBox) {
            GL11.glPushMatrix();
            TileStorageBox tileStorageBox = (TileStorageBox) tile;
            RenderOverlay.renderISWithTextOnSide(tileStorageBox, tileStorageBox.getStackInSlot(0), x, y, z, tileStorageBox.getFacing());
            GL11.glPopMatrix();
        }
    }
}
