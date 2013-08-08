package num.complexwiring.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import num.complexwiring.ComplexWiring;
import num.complexwiring.api.IConnectable;
import num.complexwiring.api.IItemWire;
import num.complexwiring.util.WireHelper;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class WireModelRenderer extends TileEntitySpecialRenderer {

    public static final ModelWire model = new ModelWire();

    public void renderModel(IConnectable wire, double x, double y, double z, boolean north, boolean south,
            boolean west, boolean east, boolean up, boolean down) {
        Minecraft.getMinecraft().renderEngine.bindTexture("/mods/ComplexWiring/textures/blocks/wireModel.png");
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GL11.glScalef(1.0F, -1F, -1F);
        
        if(ComplexWiring.DEBUG) {
            System.out.println("north: " + north + "; south: " + south + "; west: " + west + "; east: " + east + "; up: " + up + "; down: " + down);
        }
        
        if (north) {
            model.renderBack();
        } else {
            model.renderCenterBack2();
        }
        if (south) {
            model.renderFront();
        } else {
            model.renderCenterFront2();
        }
        if (west) {
            // model.renderLeft();
            model.renderRight();
        } else {
            // model.renderCenterLeft();
            model.renderCenterRight();
        }
        if (east) {
            // model.renderRight();
            model.renderLeft();
        } else {
            // model.renderCenterRight();
            model.renderCenterLeft();
        }
        if (up) {
            model.renderUp();
        } else {
            model.renderCenterUp();
        }
        if (down) {
            model.renderDown();
        } else {
            model.renderCenterDown();
        }

        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {
        IItemWire wire = (IItemWire) tile;
        renderModel(wire, x, y, z, WireHelper.getConnection(ForgeDirection.NORTH, wire),
                WireHelper.getConnection(ForgeDirection.SOUTH, wire),
                WireHelper.getConnection(ForgeDirection.WEST, wire),
                WireHelper.getConnection(ForgeDirection.EAST, wire), 
                WireHelper.getConnection(ForgeDirection.UP, wire),
                WireHelper.getConnection(ForgeDirection.DOWN, wire));
    }
}
