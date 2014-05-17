package num.complexwiring.core;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import num.complexwiring.api.vec.Vector3;
import num.complexwiring.machine.basic.*;
import num.complexwiring.machine.powered.*;
import num.complexwiring.power.electrical.battery.ContainerBattery;
import num.complexwiring.power.electrical.battery.GuiBattery;
import num.complexwiring.power.electrical.battery.TileBattery;
import num.complexwiring.power.electrical.generator.ContainerGenerator;
import num.complexwiring.power.electrical.generator.GuiGenerator;
import num.complexwiring.power.electrical.generator.TileGenerator;
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
        if (tile instanceof TileEntityBasicOrelyzer && vec3.blockMetadata(world) == EnumBasicMachine.VALID[0].ordinal()) {
            return new ContainerMachineBasicOrelyzer(player.inventory, (TileEntityBasicOrelyzer) tile);         // Basic Orelyzer
        } else if (tile instanceof TileEntityBasicFurnace && vec3.blockMetadata(world) == EnumBasicMachine.VALID[1].ordinal()) {
            return new ContainerMachineBasicFurnace(player.inventory, (TileEntityBasicFurnace) tile);     // Basic Furnace
        } else if (tile instanceof TileEntityBasicCrusher && vec3.blockMetadata(world) == EnumBasicMachine.VALID[2].ordinal()) {
            return new ContainerMachineBasicCrusher(player.inventory, (TileEntityBasicCrusher) tile);     // Basic Crusher
        } else if (tile instanceof TileEntityBCPoweredOrelyzer && vec3.blockMetadata(world) == EnumPoweredMachine.VALID[0].ordinal()) {
            return new ContainerMachinePoweredOrelyzer(player.inventory, (TileEntityBCPoweredOrelyzer) tile);     // Powered Orelyzer
        } else if (tile instanceof TileEntityBCPoweredFurnace && vec3.blockMetadata(world) == EnumPoweredMachine.VALID[1].ordinal()) {
            return new ContainerMachinePoweredFurnace(player.inventory, (TileEntityBCPoweredFurnace) tile);     // Powered Furnace
        } else if (tile instanceof TileEntityBCPoweredCrusher && vec3.blockMetadata(world) == EnumPoweredMachine.VALID[2].ordinal()) {
            return new ContainerMachinePoweredCrusher(player.inventory, (TileEntityBCPoweredCrusher) tile);     // Powered Crusher
        } else if (tile instanceof TileGenerator) {
            return new ContainerGenerator(player.inventory, (TileGenerator) tile);
        } else if (tile instanceof TileBattery) {
            return new ContainerBattery(player.inventory, (TileBattery) tile);
        } else if (ID == GUIDEBOOK_ID) {
            return new ContainerGuidebook(player.inventory);
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
        } else if (tile instanceof TileEntityBCPoweredOrelyzer && vec3.blockMetadata(world) == EnumPoweredMachine.VALID[0].ordinal()) {
            return new GuiMachinePoweredOrelyzer(player.inventory, (TileEntityBCPoweredOrelyzer) tile);          // Powered Orelyzer
        } else if (tile instanceof TileEntityBCPoweredFurnace && vec3.blockMetadata(world) == EnumPoweredMachine.VALID[1].ordinal()) {
            return new GuiMachinePoweredFurnace(player.inventory, (TileEntityBCPoweredFurnace) tile);          // Powered Furnace
        } else if (tile instanceof TileEntityBCPoweredCrusher && vec3.blockMetadata(world) == EnumPoweredMachine.VALID[2].ordinal()) {
            return new GuiMachinePoweredCrusher(player.inventory, (TileEntityBCPoweredCrusher) tile);          // Powered Crusher
        } else if (tile instanceof TileGenerator) {
            return new GuiGenerator(player.inventory, (TileGenerator) tile);
        } else if (tile instanceof TileBattery) {
            return new GuiBattery(player.inventory, (TileBattery) tile);
        } else if (ID == TABLET_BASIC_ID) {
            return new GuiTablet(player, 0);       // The Tablet!
        } else if (ID == STARTPAPER_ID) {
            return new GuiStartPaper(player);
        } else if (ID == GUIDEBOOK_ID) {
            return new GuiGuidebook(player);
        }
        return null;

    }
}
