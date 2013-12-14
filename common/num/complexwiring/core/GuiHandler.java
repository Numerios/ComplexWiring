package num.complexwiring.core;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import num.complexwiring.api.vec.Vector3;
import num.complexwiring.machine.basic.*;
import num.complexwiring.machine.powered.ContainerMachinePoweredOrelyzer;
import num.complexwiring.machine.powered.EnumPoweredMachine;
import num.complexwiring.machine.powered.GuiMachinePoweredOrelyzer;
import num.complexwiring.machine.powered.TileEntityPoweredOrelyzer;
import num.complexwiring.tablet.GuiTablet;

public class GuiHandler implements IGuiHandler {
    public static final int TABLET_BASIC_ID = 42;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        Vector3 vec3 = new Vector3(x, y, z);
        TileEntity tile = vec3.toTile(world);
        if (tile instanceof TileEntityBasicOrelyzer && vec3.blockMetadata(world) == EnumBasicMachine.VALID[0].ordinal()) {
            return new ContainerMachineBasicOrelyzer(player.inventory, (TileEntityBasicOrelyzer) tile);         // Basic Orelyzer
        } else if (tile instanceof TileEntityBasicFurnace && vec3.blockMetadata(world) == EnumBasicMachine.VALID[1].ordinal()) {
            return new ContainerMachineBasicFurnace(player.inventory, (TileEntityBasicFurnace) tile);     // Basic Furnace
        } else if (tile instanceof TileEntityPoweredOrelyzer && vec3.blockMetadata(world) == EnumPoweredMachine.VALID[0].ordinal()) {
            return new ContainerMachinePoweredOrelyzer(player.inventory, (TileEntityPoweredOrelyzer) tile);     // Powered Orelyzer
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        Vector3 vec3 = new Vector3(x, y, z);
        TileEntity tile = vec3.toTile(world);
        if (tile instanceof TileEntityBasicOrelyzer && vec3.blockMetadata(world) == EnumBasicMachine.VALID[0].ordinal()) {
            return new GuiMachineBasicOrelyzer(player.inventory, (TileEntityBasicOrelyzer) tile);              // Basic Orelyzer
        } else if (tile instanceof TileEntityBasicFurnace && vec3.blockMetadata(world) == EnumBasicMachine.VALID[1].ordinal()) {
            Logger.debug("\\o I\'m TEBasicFurnace!");
            return new GuiMachineBasicFurnace(player.inventory, (TileEntityBasicFurnace) tile);          // Basic Furnace
        } else if (tile instanceof TileEntityPoweredOrelyzer && vec3.blockMetadata(world) == EnumPoweredMachine.VALID[0].ordinal()) {
            return new GuiMachinePoweredOrelyzer(player.inventory, (TileEntityPoweredOrelyzer) tile);          // Powered Orelyzer
        } else if (ID == TABLET_BASIC_ID){
            return new GuiTablet(player);       // The Tablet!
        }
        return null;

    }
}
