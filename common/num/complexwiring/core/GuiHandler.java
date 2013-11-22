package num.complexwiring.core;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import num.complexwiring.api.vec.Vector3;
import num.complexwiring.client.GuiMachineBasic;
import num.complexwiring.machine.TileEntityMachineBasic;

public class GuiHandler implements IGuiHandler {

    public static final int GUI_MACHINE_BASIC = 1;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        Vector3 vec3 = new Vector3(x, y, z);
        if (vec3.blockExists(world)) {
            switch (ID) {
                case GUI_MACHINE_BASIC:
                    return new GuiMachineBasic(player.inventory, (TileEntityMachineBasic) vec3.toTile(world));
            }
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        Logger.debug("NEW MACHINE GUI!");
        Vector3 vec3 = new Vector3(x, y, z);
        if (vec3.blockExists(world)) {
            switch (ID) {
                case GUI_MACHINE_BASIC:
                    Logger.debug("I SUCCEEDED!");
                    return new GuiMachineBasic(player.inventory, (TileEntityMachineBasic) vec3.toTile(world));
            }
        }
        Logger.debug("I FAILED!");
        return null;
    }
}
