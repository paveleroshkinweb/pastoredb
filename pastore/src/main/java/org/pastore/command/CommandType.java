package org.pastore.command;

import org.pastore.command.format.*;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum CommandType {

    GET("GET", null),
    SET("SET", new SetFormat()), // +
    INCREMENT("INCREMENT", new IncrementFormat()), //+
    EXISTS("EXISTS", new ExistsFormat()), // +
    INDEX("INDEX", null),
    PUSH("PUSH", new PushFormat()), // +
    POP("POP", null),
    SHIFT("SHIFT", null),
    UNSHIFT("UNSHIFT", null),
    SUBSCRIBE("SUB", null),
    UNSUBSCRIBE("UNSUB", null),
    LOGIN("LOGIN", null),
    USE("USE", new UseFormat()), // +
    DB("DB", new DBFormat()), // +
    EXIT("EXIT", new ExitFormat()); // +

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
