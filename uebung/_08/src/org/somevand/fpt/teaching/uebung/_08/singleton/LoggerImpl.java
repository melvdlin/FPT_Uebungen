package org.somevand.fpt.teaching.uebung._08.singleton;

import java.io.PrintStream;
import java.time.LocalDateTime;

public enum LoggerImpl implements Logger {
    STDERR(System.err),
    STDOUT(System.out),
    ;

    //                                          YYYY-MM-DD, HH:MM:SS.mmmm - [severity]
    private static String preambleFormat = "%04d-%02d-%02d, %02d:%02d:%02d.%04d - [%s]";
    private static String messageFormat = "%s";
    private static String logEntryFormat = "%-38s: %s%n";
    private PrintStream logStream;

    private LoggerImpl(PrintStream logStream) {
        this.logStream = logStream;
    }

    @Override
    public void log(Level severity, String message) {
        final LocalDateTime now = LocalDateTime.now();
        logStream.printf(
                logEntryFormat,
                preambleFormat.formatted(
                        now.getYear(),
                        now.getMonth().getValue(),
                        now.getDayOfMonth(),
                        now.getHour(),
                        now.getMinute(),
                        now.getSecond(),
                        now.getNano() / 1_000_000,
                        severity
                ),
                messageFormat.formatted(
                        message
                )
        );
    }

    @Override
    public void logDebug(String message) {
        log(Level.DEBUG, message);
    }

    @Override
    public void logInfo(String message) {
        log(Level.INFO, message);
    }

    @Override
    public void logWarning(String message) {
        log(Level.WARNING, message);
    }


    @Override
    public void logError(String message) {
        log(Level.ERROR, message);
    }

    public static void main(String[] args) {
        LoggerImpl.STDERR.logDebug("some debug info!");
        LoggerImpl.STDERR.logInfo("some info!");
        LoggerImpl.STDERR.logWarning("some warning!");
        LoggerImpl.STDERR.logError("some error!");
        LoggerImpl.STDOUT.logDebug("some debug info!");
        LoggerImpl.STDOUT.logInfo("some info!");
        LoggerImpl.STDOUT.logWarning("some warning!");
        LoggerImpl.STDOUT.logError("some error!");
    }
}
