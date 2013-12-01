package num.complexwiring.machine;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import num.complexwiring.lib.ModuleBase;
import num.complexwiring.world.EnumOrePrimary;
import num.complexwiring.world.EnumOreSecondary;

public class ModuleMachine extends ModuleBase {
    public static Block machine;

    @Override
    public void preInit() {
        registerBlocks();
        registerItems();
    }

    @Override
    public void init() {
        registerRecipes();
    }

    private void registerBlocks() {
        machine = new BlockMachine(669);
        GameRegistry.registerBlock(machine, ItemBlockMachine.class, machine.getUnlocalizedName());
        ((BlockMachine) machine).registerTiles();
    }

    private void registerItems() {

    }

    private void registerRecipes() {
        MachineBasicRecipes.add(EnumOrePrimary.COPPER.getIS(1), EnumOreSecondary.CHALCOPYRITE.getIS(1));
        MachineBasicRecipes.add(EnumOrePrimary.TIN.getIS(1), EnumOreSecondary.CASSITERITE.getIS(1));
    }
}
