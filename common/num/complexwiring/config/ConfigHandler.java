package num.complexwiring.config;

import net.minecraftforge.common.config.Configuration;
import num.complexwiring.core.Logger;

import java.io.File;

public class ConfigHandler {
    public static Configuration conf;

    public static void init(File configFile) {
        conf = new Configuration(configFile);
        conf.load();
    }

    public static void save() {
        conf.save();
    }
}
