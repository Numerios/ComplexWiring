package num.complexwiring.machine.smasher;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import num.complexwiring.core.Logger;
import num.complexwiring.lib.Reference;

import static org.lwjgl.opengl.GL11.*;

@SideOnly(Side.CLIENT)
public class RenderSmasher extends TileEntitySpecialRenderer {
    IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation(Reference.MOD_ID.toLowerCase(), "models/smasher.tcn"));
    ResourceLocation texture = new ResourceLocation(Reference.MOD_ID.toLowerCase(), "models/smasher.png");

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {
        TileSmasher smasher = (TileSmasher) tile;

        glPushMatrix();
        glTranslated(x + 0.5D, y + 0.5D, z + 0.5D);

        glShadeModel(GL_SMOOTH);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        switch (smasher.getFacing()) {
            case DOWN:
                glRotatef(180, 0, 0, 1);
                break;
            case SOUTH:
                glRotatef(90, 1, 0, 0);
                break;
            case NORTH:
                glRotatef(-90, 1, 0, 0);
                break;
            case WEST:
                glRotatef(90, 0, 0, 1);
                break;
            case EAST:
                glRotatef(-90, 0, 0, 1);
                break;
        }

        FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);
        model.renderOnly("Block Base", "Block Interface");

        glRotatef(((TileSmasher) tile).getProgress(180) * 3, 0, 1, 0);               //very wild guess
        model.renderAllExcept("Block Base", "Block Interface");

        glShadeModel(GL_FLAT);
        glDisable(GL_LINE_SMOOTH);
        glDisable(GL_POLYGON_SMOOTH);
        glDisable(GL_BLEND);

        glPopMatrix();
    }

}
