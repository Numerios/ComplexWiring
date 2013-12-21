package num.complexwiring.core;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import num.complexwiring.api.vec.Vector3;
import num.complexwiring.machine.basic.*;
import num.complexwiring.machine.powered.*;
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
        if (tile instanceof TileEntityBasicOrelyzer && vec3.blockMetadata(world) == EnumBasicMachine.VALID[0].ordinal()) {
            return new ContainerMachineBasicOrelyzer(player.inventory, (TileEntityBasicOrelyzer) tile);         // Basic Orelyzer
        } else if (tile instanceof TileEntityBasicFurnace && vec3.blockMetadata(world) == EnumBasicMachine.VALID[1].ordinal()) {
            return new ContainerMachineBasicFurnace(player.inventory, (TileEntityBasicFurnace) tile);     // Basic Furnace
        } else if (tile instanceof TileEntityBasicCrusher && vec3.blockMetadata(world) == EnumBasicMachine.VALID[2].ordinal()) {
            return new ContainerMachineBasicCrusher(player.inventory, (TileEntityBasicCrusher) tile);     // Basic Crusher
        } else if (tile instanceof TileEntityPoweredOrelyzer && vec3.blockMetadata(world) == EnumPoweredMachine.VALID[0].ordinal()) {
            return new ContainerMachinePoweredOrelyzer(player.inventory, (TileEntityPoweredOrelyzer) tile);     // Powered Orelyzer
        } else if (tile instanceof TileEntityPoweredFurnace && vec3.blockMetadata(world) == EnumPoweredMachine.VALID[1].ordinal()) {
            return new ContainerMachinePoweredFurnace(player.inventory, (TileEntityPoweredFurnace) tile);     // Powered Furnace
        } else if (tile instanceof TileEntityPoweredCrusher && vec3.blockMetadata(world) == EnumPoweredMachine.VALID[2].ordinal()) {
            return new ContainerMachinePoweredCrusher(player.inventory, (TileEntityPoweredCrusher) tile);     // Powered Crusher
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
            return new GuiMachineBasicFurnace(player.inventory, (TileEntityBasicFurnace) tile);          // Basic Furnace
        } else if (tile instanceof TileEntityBasicCrusher && vec3.blockMetadata(world) == EnumBasicMachine.VALID[2].ordinal()) {
            return new GuiMachineBasicCrusher(player.inventory, (TileEntityBasicCrusher) tile);          // Basic Crusher
        } else if (tile instanceof TileEntityPoweredOrelyzer && vec3.blockMetadata(world) == EnumPoweredMachine.VALID[0].ordinal()) {
            return new GuiMachinePoweredOrelyzer(player.inventory, (TileEntityPoweredOrelyzer) tile);          // Powered Orelyzer
        } else if (tile instanceof TileEntityPoweredFurnace && vec3.blockMetadata(world) == EnumPoweredMachine.VALID[1].ordinal()) {
            return new GuiMachinePoweredFurnace(player.inventory, (TileEntityPoweredFurnace) tile);          // Powered Furnace
        } else if (tile instanceof TileEntityPoweredCrusher && vec3.blockMetadata(world) == EnumPoweredMachine.VALID[2].ordinal()) {
            return new GuiMachinePoweredCrusher(player.inventory, (TileEntityPoweredCrusher) tile);          // Powered Crusher
        } else if (ID == TABLET_BASIC_ID){
            return new GuiTablet(player, 0);       // The Tablet!
        } else if (ID == STARTPAPER_ID){
            return new GuiStartPaper(player);
        } else if (ID >= GUIDEBOOK_ID && ID <= GUIDEBOOK_ID + 100){
            return new GuiGuidebook(player, ID - GUIDEBOOK_ID);
        }
        return null;

    }
}
