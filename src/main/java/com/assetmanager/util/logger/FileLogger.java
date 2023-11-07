package com.assetmanager.util.logger;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Formatter;

public class FileLogger extends Formatter {
    private static Logger LOGGER;

    public static Logger getLogger() {
        if (LOGGER == null) {
            LOGGER = Logger.getLogger(FileLogger.class.getName());

            try {
                FileHandler fileHandler = new FileHandler("assetmanager.log");
                fileHandler.setFormatter(new SimpleFormatter());
                LOGGER.addHandler(fileHandler);
                LOGGER.setLevel(Level.INFO);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return LOGGER;
    }

    @Override
    public String format(LogRecord record) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String method = record.getSourceMethodName();
        String level = record.getLevel().getName();
        String message = record.getMessage();
        Instant instant = record.getInstant();
        LocalDateTime now = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return pattern.format(now) + " | " + method + " | " + level + " | " + message + "\n";
    }
}
