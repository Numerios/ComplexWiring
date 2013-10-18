package num.complexwiring.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeDirection;
import num.complexwiring.api.IItemConnectable;
import num.complexwiring.block.TileItemWire;
import num.complexwiring.core.Wrapper;
import num.complexwiring.lib.Reference;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class WireModelRenderer extends TileEntitySpecialRenderer {

    public static final ModelWire model = new ModelWire();
    private static ResourceLocation MODEL_TEXTURE = new ResourceLocation(Reference.TEXTURE_PATH
            + "textures/blocks/wireModel.png");

    public void renderModel(IItemConnectable wire, double x, double y, double z) {
        Minecraft.getMinecraft().renderEngine.bindTexture(MODEL_TEXTURE);

        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GL11.glScalef(1.0F, -1F, -1F);

        if (wire.getConnection(ForgeDirection.SOUTH)) {
            model.front1.render(0.0625F);
            model.front2.render(0.0625F);
            model.front3.render(0.0625F);
            model.front4.render(0.0625F);
        } else {
            if (!wire.getConnection(ForgeDirection.EAST)) {
                model.centerFront2.render(0.0625F);
            }
            if (!wire.getConnection(ForgeDirection.WEST)) {
                model.centerFront1.render(0.0625F);
            }
        }
        if (wire.getConnection(ForgeDirection.WEST)) {
            model.right1.render(0.0625F);
            model.right2.render(0.0625F);
            model.right3.render(0.0625F);
            model.right4.render(0.0625F);
        }
        if (wire.getConnection(ForgeDirection.NORTH)) {
            model.back1.render(0.0625F);
            model.back2.render(0.0625F);
            model.back3.render(0.0625F);
            model.back4.render(0.0625F);
        } else {
            if (!wire.getConnection(ForgeDirection.EAST)) {
                model.centerBack1.render(0.0625F);
            }
            if (!wire.getConnection(ForgeDirection.WEST)) {
                model.centerBack2.render(0.0625F);
            }
        }
        if (wire.getConnection(ForgeDirection.EAST)) {
            model.left1.render(0.0625F);
            model.left2.render(0.0625F);
            model.left3.render(0.0625F);
            model.left4.render(0.0625F);
        }
        if (wire.getConnection(ForgeDirection.UP)) {
            model.up1.render(0.0625F);
            model.up2.render(0.0625F);
            model.up3.render(0.0625F);
            model.up4.render(0.0625F);
        } else {
            if (!wire.getConnection(ForgeDirection.SOUTH)) {
                model.centerUp2.render(0.0625F);
            }
            if (!wire.getConnection(ForgeDirection.NORTH)) {
                model.centerUp1.render(0.0625F);
            }
            if (!wire.getConnection(ForgeDirection.EAST)) {
                model.centerLeft1.render(0.0625F);
            }
            if (!wire.getConnection(ForgeDirection.WEST)) {
                model.centerRight1.render(0.0625F);
            }
        }
        if (wire.getConnection(ForgeDirection.DOWN)) {
            model.down1.render(0.0625F);
            model.down2.render(0.0625F);
            model.down3.render(0.0625F);
            model.down4.render(0.0625F);
        } else {
            if (!wire.getConnection(ForgeDirection.SOUTH)) {
                model.centerDown2.render(0.0625F);
            }
            if (!wire.getConnection(ForgeDirection.NORTH)) {
                model.centerDown1.render(0.0625F);
            }
            if (!wire.getConnection(ForgeDirection.EAST)) {
                model.centerLeft2.render(0.0625F);
            }
            if (!wire.getConnection(ForgeDirection.WEST)) {
                model.centerRight2.render(0.0625F);
            }
        }

        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {
        TileItemWire wire = (TileItemWire) tile;
        renderModel(wire, x, y, z);
        for (Wrapper wrapper : wire.contents) {
            WrapperRenderer.render(wire, x, y, z, wrapper);
        }
    }
}
