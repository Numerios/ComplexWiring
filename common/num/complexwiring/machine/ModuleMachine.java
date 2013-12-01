package num.complexwiring.machine;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import num.complexwiring.lib.ModuleBase;
import num.complexwiring.world.EnumOrePrimary;
import num.complexwiring.world.EnumOreSecondary;

public class ModuleMachine extends ModuleBase {
    public static Block machineBasic;

    public void preInit() {
        //TODO: IDs, srsly...
        machineBasic = new BlockMachineBasic(668);
        GameRegistry.registerBlock(machineBasic, machineBasic.getUnlocalizedName());
        GameRegistry.registerTileEntity(TileEntityMachineBasic.class, machineBasic.getUnlocalizedName());
    }

    public void init() {
        MachineBasicRecipes.add(EnumOrePrimary.COPPER.getIS(1), EnumOreSecondary.CHALCOPYRITE.getIS(1));
        MachineBasicRecipes.add(EnumOrePrimary.TIN.getIS(1), EnumOreSecondary.CASSITERITE.getIS(1));

    }
}
