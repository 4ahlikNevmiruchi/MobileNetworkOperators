package com.ideaprojects.mno.util;

import java.util.logging.*;

/**
 * Simple centralized logger configuration based on java.util.logging.
 * Ensures a single ConsoleHandler with SimpleFormatter and INFO level by default.
 * When running under unit tests (Surefire/JUnit), suppress INFO to reduce noise.
 */
public final class Log {
    private static volatile boolean initialized = false;

    private Log() {}

    public static Logger getLogger(Class<?> cls) {
        ensureConfigured();
        return Logger.getLogger(cls.getName());
    }

    private static synchronized void ensureConfigured() {
        if (initialized) return;
        Logger root = Logger.getLogger("");
        // Remove default handlers to avoid duplicate logs
        for (Handler h : root.getHandlers()) {
            root.removeHandler(h);
        }
        ConsoleHandler console = new ConsoleHandler();
        Level level = determineLevel();
        console.setLevel(level);
        console.setFormatter(new SimpleFormatter());
        root.addHandler(console);
        root.setLevel(level);
        initialized = true;
    }

    private static Level determineLevel() {
        // Allow override via system property: -Dmno.log.level=FINE/INFO/WARNING/SEVERE
        String prop = System.getProperty("mno.log.level");
        if (prop != null) {
            try {
                return Level.parse(prop);
            } catch (IllegalArgumentException ignored) {
                // fall through
            }
        }
        // Detect common indicators of unit test environments
        boolean underSurefire = System.getProperty("surefire.test.class.path") != null;
        boolean underJunit = System.getProperty("junit.platform.version") != null;
        if (underSurefire || underJunit) {
            return Level.WARNING; // silence info/debug during tests
        }
        return Level.INFO;
    }
}
