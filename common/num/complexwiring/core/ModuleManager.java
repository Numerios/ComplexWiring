package num.complexwiring.core;

import num.complexwiring.base.ModuleBase;
import num.complexwiring.lib.Module;
import num.complexwiring.machine.ModuleMachine;
import num.complexwiring.tablet.ModuleTablet;
import num.complexwiring.world.ModuleWorld;

import java.util.ArrayList;

public class ModuleManager {
    private static ArrayList<Module> modules = new ArrayList<Module>();

    public static void preInit() {
        modules.add(new ModuleBase());
        modules.add(new ModuleTablet());
        modules.add(new ModuleWorld());
        modules.add(new ModuleMachine());

        for (Module module : modules) {
            module.preInit();
        }
    }

    public static void init() {
        for (Module module : modules) {
            module.init();
        }
    }
}
