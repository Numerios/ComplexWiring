package num.complexwiring.machine;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import num.complexwiring.lib.ModuleBase;

public class ModuleMachine extends ModuleBase {
    public static Block machineBasic;

    public void preInit() {
        //TODO: IDs, srsly...
        machineBasic = new BlockMachineBasic(668);
        GameRegistry.registerBlock(machineBasic, machineBasic.getUnlocalizedName());
        GameRegistry.registerTileEntity(TileEntityMachineBasic.class, machineBasic.getUnlocalizedName());
    }
}
