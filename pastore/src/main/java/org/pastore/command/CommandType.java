package org.pastore.command;

import org.pastore.command.format.*;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum CommandType {

    GET("GET", new KeyFormat(), true),
    SET("SET", new SetFormat(), true),
    DELETE("DELETE", new KeyFormat(), true),
    INCREMENT("INCREMENT", new KeyFormat(), true),
    EXISTS("EXISTS", new KeyFormat(), true),
    INDEX("INDEX", new KeyValueFormat(), true),
    SIZE("SIZE", new KeyFormat(), true),
    PUSH("PUSH", new KeyValueFormat(), true),
    POP("POP", new KeyFormat(), true),
    SHIFT("SHIFT", new KeyFormat(), true),
    UNSHIFT("UNSHIFT", new KeyValueFormat(), true),
    LOGIN("LOGIN", new KeyFormat(), false),
    USE("USE", new KeyFormat(), true),
    DB("DB", new PlainFormat(), true),
    EXIT("EXIT", new PlainFormat(), false);

    private final String name;

    private final Format format;

    private boolean loginRequired;

    private static final Map<String, CommandType> types = Arrays.stream(CommandType.values()).collect(
            Collectors.toMap(CommandType::getName, command -> command)
    );

    CommandType(final String name, final Format format, final boolean loginRequired) {
        this.name = name;
        this.format = format;
        this.loginRequired = loginRequired;
    }

    public boolean isLoginRequired() {
        return loginRequired;
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
