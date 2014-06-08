package num.complexwiring.machine.storagebox;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import num.complexwiring.api.vec.Vector3;
import num.complexwiring.client.RenderOverlay;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderStorageBox extends TileEntitySpecialRenderer {

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {
        if (tile instanceof TileStorageBox) {
            GL11.glPushMatrix();
            TileStorageBox tileStorageBox = (TileStorageBox) tile;

            Vector3 facingTowardsTo = tileStorageBox.pos().add(tileStorageBox.getFacing().offsetX, tileStorageBox.getFacing().offsetY, tileStorageBox.getFacing().offsetZ);
            Block block = tileStorageBox.world().getBlock(facingTowardsTo.getX(), facingTowardsTo.getY(), facingTowardsTo.getZ());
            if (!block.isOpaqueCube()) {
                RenderOverlay.renderISWithTextOnSide(tileStorageBox, tileStorageBox.getContaining(), x, y, z, tileStorageBox.getFacing());
            }
            GL11.glPopMatrix();
        }
    }
}
