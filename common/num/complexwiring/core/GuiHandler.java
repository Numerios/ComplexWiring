package num.complexwiring.core;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import num.complexwiring.api.vec.Vector3;
import num.complexwiring.machine.ContainerMachineBasic;
import num.complexwiring.machine.GuiMachineBasic;
import num.complexwiring.machine.TileEntityMachineBasic;

public class GuiHandler implements IGuiHandler {

    public static final int GUI_MACHINE_BASIC = 1;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        Vector3 vec3 = new Vector3(x, y, z);
        TileEntity tile = vec3.toTile(world);
        if (tile instanceof TileEntityMachineBasic && ID == GUI_MACHINE_BASIC)
            return new ContainerMachineBasic(player.inventory, (TileEntityMachineBasic) tile);
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        Vector3 vec3 = new Vector3(x, y, z);
        if (vec3.blockExists(world)) {
            TileEntity tile = vec3.toTile(world);
            if (tile instanceof TileEntityMachineBasic && ID == GUI_MACHINE_BASIC)
                return new GuiMachineBasic(player.inventory, (TileEntityMachineBasic) tile);

        }
        return null;
    }
}
