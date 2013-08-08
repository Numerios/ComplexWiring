package num.complexwiring.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import num.complexwiring.api.IConnectable;
import num.complexwiring.api.IItemWire;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class WireModelRenderer extends TileEntitySpecialRenderer {

    public static final ModelWire model = new ModelWire();

    public void renderModelTwo(IConnectable wire, double x, double y, double z) {
        Minecraft.getMinecraft().renderEngine.bindTexture("/mods/ComplexWiring/textures/blocks/wireModel.png");

        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GL11.glScalef(1.0F, -1F, -1F);
        // System.out.println("north: " + north + "; south: " + south +
        // "; west: " + west + "; east: " + east + "; up: "
        // + up + "; down: " + down);
        if (wire.getConnection(ForgeDirection.SOUTH, wire)) {
            model.front1.render(0.0625F);
            model.front2.render(0.0625F);
            model.front3.render(0.0625F);
            model.front4.render(0.0625F);
        } else {
            if (!wire.getConnection(ForgeDirection.EAST, wire)) {
                model.centerFront2.render(0.0625F);
            }
            if (!wire.getConnection(ForgeDirection.WEST, wire)) {
                model.centerFront1.render(0.0625F);
            }
        }
        if (wire.getConnection(ForgeDirection.WEST, wire)) {
            model.right1.render(0.0625F);
            model.right2.render(0.0625F);
            model.right3.render(0.0625F);
            model.right4.render(0.0625F);
        }
        if (wire.getConnection(ForgeDirection.NORTH, wire)) {
            model.back1.render(0.0625F);
            model.back2.render(0.0625F);
            model.back3.render(0.0625F);
            model.back4.render(0.0625F);
        } else {
            if (!wire.getConnection(ForgeDirection.EAST, wire)) {
                model.centerBack1.render(0.0625F);
            }
            if (!wire.getConnection(ForgeDirection.WEST, wire)) {
                model.centerBack2.render(0.0625F);
            }
        }
        if (wire.getConnection(ForgeDirection.EAST, wire)) {
            model.left1.render(0.0625F);
            model.left2.render(0.0625F);
            model.left3.render(0.0625F);
            model.left4.render(0.0625F);
        }
        if (wire.getConnection(ForgeDirection.UP, wire)) {
            model.up1.render(0.0625F);
            model.up2.render(0.0625F);
            model.up3.render(0.0625F);
            model.up4.render(0.0625F);
        } else {
            if (!wire.getConnection(ForgeDirection.SOUTH, wire)) {
                model.centerUp2.render(0.0625F);
            }
            if (!wire.getConnection(ForgeDirection.NORTH, wire)) {
                model.centerUp1.render(0.0625F);
            }
            if (!wire.getConnection(ForgeDirection.EAST, wire)) {
                model.centerLeft1.render(0.0625F);
            }
            if (!wire.getConnection(ForgeDirection.WEST, wire)) {
                model.centerRight1.render(0.0625F);
            }
        }
        if (wire.getConnection(ForgeDirection.DOWN, wire)) {
            model.down1.render(0.0625F);
            model.down2.render(0.0625F);
            model.down3.render(0.0625F);
            model.down4.render(0.0625F);
        } else {
            if (!wire.getConnection(ForgeDirection.SOUTH, wire)) {
                model.centerDown2.render(0.0625F);
            }
            if (!wire.getConnection(ForgeDirection.NORTH, wire)) {
                model.centerDown1.render(0.0625F);
            }
            if (!wire.getConnection(ForgeDirection.EAST, wire)) {
                model.centerLeft2.render(0.0625F);
            }
            if (!wire.getConnection(ForgeDirection.WEST, wire)) {
                model.centerRight2.render(0.0625F);
            }
        }

        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {
        IItemWire wire = (IItemWire) tile;
        renderModelTwo(wire, x, y, z);
    }
}
