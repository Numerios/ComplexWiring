package num.complexwiring.core;

import num.complexwiring.ComplexWiring;
import num.complexwiring.lib.Reference;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

public class Logger {
    private static final org.apache.logging.log4j.Logger cwLogger = LogManager.getLogger(Reference.MOD_ID);

    public static void init() {
    }

    public static void log(Level level, String message) {
        cwLogger.log(level, message);
    }

    public static void error(String message) {
        log(Level.ERROR, message);
    }

    public static void warn(String message) {
        log(Level.WARN, message);
    }

    public static void info(String message) {
        log(Level.INFO, message);
    }

    public static void debug(String message) {
        if (ComplexWiring.DEBUG)
            log(Level.WARN, message);  //TODO: DO THE THING WITH log4j.properities!
    }
}
