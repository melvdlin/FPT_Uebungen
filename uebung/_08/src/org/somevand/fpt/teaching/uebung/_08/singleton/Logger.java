package org.somevand.fpt.teaching.uebung._08.singleton;

import java.util.Arrays;
import java.util.List;

public interface Logger {
    enum Level {
        DEBUG,
        INFO,
        WARNING,
        ERROR,
        ;

        public static final List<Level> levels = List.copyOf(Arrays.asList(values()));
    }

    void log(Level severity, String message);

    // convenience methods
    void logDebug(String message);
    void logInfo(String message);
    void logWarning(String message);
    void logError(String message);
}
