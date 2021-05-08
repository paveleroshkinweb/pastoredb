package org.pastore.logging;

import org.apache.log4j.Level;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public enum LogLevel {

    DEBUG("debug", Level.DEBUG),
    INFO("info", Level.INFO),
    WARN("warn", Level.WARN),
    ERROR("error", Level.ERROR);

    private static final Map<String, LogLevel> levels = Arrays.stream(LogLevel.values()).collect(
            Collectors.toMap(LogLevel::getName, level -> level)
    );

    private final String name;

    private final Level level;

    LogLevel(String name, Level level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public Level getLevel() {
        return level;
    }

    public static Set<String> availableLogLevels() {
        return levels.keySet();
    }

    public static LogLevel getLevelByName(String name) {
        return levels.get(name);
    }

}
