package num.complexwiring.core;

import num.complexwiring.lib.ModuleBase;
import num.complexwiring.machine.ModuleMachine;
import num.complexwiring.world.ModuleWorld;

import java.util.ArrayList;

public class ModuleManager {
    private static ArrayList<ModuleBase> modules = new ArrayList<ModuleBase>();

    public static void preInit() {
        modules.add(new ModuleWorld());
        modules.add(new ModuleMachine());

        for (ModuleBase module : modules) {
            module.preInit();
        }
    }

    public static void init() {
        for (ModuleBase module : modules) {
            module.init();
        }
    }
}
