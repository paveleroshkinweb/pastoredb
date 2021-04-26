package org.pastore.command;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum CommandType {

    GET("GET"),
    SET("SET"),
    INCREMENT("INCREMENT"),
    EXISTS("EXISTS"),
    FLUSH("FLUSH"),
    APPEND("APPEND"),
    POP("POP"),
    SUBSCRIBE("SUB"),
    LOGIN("LOGIN"),
    USE("USE"),
    DB("DB");

    final String name;

    private static final Map<String, CommandType> types = Arrays.stream(CommandType.values()).collect(
            Collectors.toMap(CommandType::getName, command -> command)
    );

    CommandType(final String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public static CommandType getCommandByName(final String name) {
        return types.get(name);
    }
}
