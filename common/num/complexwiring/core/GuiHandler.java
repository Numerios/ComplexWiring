package num.complexwiring.core;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import num.complexwiring.api.vec.Vector3;
import num.complexwiring.tablet.guidebook.ContainerGuidebook;
import num.complexwiring.tablet.guidebook.GuiGuidebook;
import num.complexwiring.tablet.startpaper.GuiStartPaper;
import num.complexwiring.tablet.tablet.GuiTablet;

public class GuiHandler implements IGuiHandler {
    public static final int TABLET_BASIC_ID = 42;
    public static final int STARTPAPER_ID = 43;
    public static final int GUIDEBOOK_ID = 300;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        Vector3 vec3 = new Vector3(x, y, z);
        TileEntity tile = vec3.toTile(world);
        if (ID == GUIDEBOOK_ID) {
            return new ContainerGuidebook(player.inventory);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        Vector3 vec3 = new Vector3(x, y, z);
        TileEntity tile = vec3.toTile(world);
        if (ID == TABLET_BASIC_ID) {
            return new GuiTablet(player, 0);       // The Tablet!
        } else if (ID == STARTPAPER_ID) {
            return new GuiStartPaper(player);
        } else if (ID == GUIDEBOOK_ID) {
            return new GuiGuidebook(player);
        }
        return null;

    }
}
