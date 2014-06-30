package num.complexwiring.core;

import net.minecraftforge.common.config.Configuration;
import num.complexwiring.ComplexWiring;
import num.complexwiring.world.ModuleWorld;

import java.io.File;

public class ConfigHandler {
    public static Configuration conf;

    public static void init(File configFile) {
        conf = new Configuration(configFile);
        conf.load();
    }

    public static void save() {
        if(conf.hasChanged()) conf.save();
    }

    public static void reloadConfig() {
        save();
        ModuleWorld.worldGenerator.loadConfig();
    }
}
