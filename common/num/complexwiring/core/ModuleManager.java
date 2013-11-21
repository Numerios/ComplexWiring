package num.complexwiring.core;

import num.complexwiring.lib.ModuleBase;
import num.complexwiring.world.ModuleWorld;

import java.util.ArrayList;

public class ModuleManager {
    private static ArrayList<ModuleBase> modules = new ArrayList<ModuleBase>();

    public static void init() {
        modules.add(new ModuleWorld());

        for (ModuleBase module : modules) {
            module.init();
        }
    }
}
