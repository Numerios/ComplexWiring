package num.complexwiring.mechanical.storagebox;

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
            TileStorageBox storageBox = (TileStorageBox) tile;

            Vector3 facing = storageBox.pos().clone().step(storageBox.getFacing());
            Block block = storageBox.world().getBlock(facing.getX(), facing.getY(), facing.getZ());
            if (!block.isOpaqueCube()) {
                RenderOverlay.renderISWithTextOnSide(storageBox, storageBox.getContaining(), x, y, z, storageBox.getFacing());
            }
            GL11.glPopMatrix();
        }
    }
}
