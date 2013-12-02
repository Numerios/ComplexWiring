package num.complexwiring.core;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import num.complexwiring.api.base.TileEntityInventoryBase;
import num.complexwiring.api.vec.Vector3;
import num.complexwiring.machine.ContainerOrelyzer;
import num.complexwiring.machine.EnumMachine;
import num.complexwiring.machine.GuiMachineOrelyzer;
import num.complexwiring.machine.TileEntityOrelyzer;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        Vector3 vec3 = new Vector3(x, y, z);
        TileEntity tile = vec3.toTile(world);
        if (tile instanceof TileEntityInventoryBase && vec3.blockMetadata(world) == EnumMachine.VALID[0].ordinal()) {
            return new ContainerOrelyzer(player.inventory, (TileEntityOrelyzer) tile);         // Ore-a-lyzer
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        Vector3 vec3 = new Vector3(x, y, z);
        TileEntity tile = vec3.toTile(world);
        if (tile instanceof TileEntityInventoryBase && vec3.blockMetadata(world) == EnumMachine.VALID[0].ordinal()) {
            return new GuiMachineOrelyzer(player.inventory, (TileEntityOrelyzer) tile);              // Ore-a-lyzer
        }
        return null;

    }
}
