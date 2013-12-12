package num.complexwiring.core;

import cpw.mods.fml.common.FMLLog;
import num.complexwiring.ComplexWiring;
import num.complexwiring.lib.Reference;

import java.util.logging.Level;

public class Logger {
    private static java.util.logging.Logger cwLogger = java.util.logging.Logger.getLogger(Reference.MOD_ID);
    public static final Level FINE = new Level("FINE", 500, defaultBundle);

    public static void init() {
        cwLogger.setParent(FMLLog.getLogger());
    }

    public static void log(Level level, String message) {
        cwLogger.log(level, message);
    }

    public static void info(String message) {
        log(Level.INFO, message);
    }

    public static void warn(String message) {
        log(Level.WARNING, message);
    }

    public static void fine(String message) {
        log(Level.FINE, message);
    }

    public static void debug(String message) {
        if (ComplexWiring.DEBUG)
            log(Level.FINE, "[DEBUG] " + message);
    }
}
