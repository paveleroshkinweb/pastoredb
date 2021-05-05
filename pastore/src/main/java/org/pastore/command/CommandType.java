package org.pastore.command;

import org.pastore.command.format.*;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum CommandType {

    GET("GET", new KeyFormat()),
    SET("SET", new SetFormat()),
    DELETE("DELETE", new KeyFormat()), //
    INCREMENT("INCREMENT", new KeyFormat()),
    EXISTS("EXISTS", new KeyFormat()),
    INDEX("INDEX", new KeyValueFormat()),
    SIZE("SIZE", new KeyFormat()),
    PUSH("PUSH", new KeyValueFormat()),
    POP("POP", new KeyFormat()),
    SHIFT("SHIFT", new KeyFormat()),
    UNSHIFT("UNSHIFT", new KeyValueFormat()),
    SUBSCRIBE("SUB", null), //
    UNSUBSCRIBE("UNSUB", null), //
    LOGIN("LOGIN", null), //
    USE("USE", new KeyFormat()),
    DB("DB", new PlainFormat()),
    EXIT("EXIT", new PlainFormat());

    private final String name;

    private final Format format;

    private static final Map<String, CommandType> types = Arrays.stream(CommandType.values()).collect(
            Collectors.toMap(CommandType::getName, command -> command)
    );

    CommandType(final String name, final Format format) {
        this.name = name;
        this.format = format;
    }

    public String getName() {
        return name;
    }

    public Format getFormat() {
        return format;
    }

    public static CommandType getCommandByName(final String name) {
        return types.get(name);
    }
}
