package num.complexwiring.core;

import num.complexwiring.lib.ModuleBase;
import num.complexwiring.world.ModuleWorld;

import java.util.ArrayList;

public class ModuleManager {
    private static ArrayList<ModuleBase> modules = new ArrayList<ModuleBase>();

    public static void load() {
        modules.add(new ModuleWorld());
    }

    public static void init() {
        for (ModuleBase module : modules) {
            module.init();
        }
    }
}
