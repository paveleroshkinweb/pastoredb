package org.pastore.command;

import org.pastore.command.format.*;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum CommandType {

    GET("GET", new KeyFormat(), true, false),
    SET("SET", new SetFormat(), true, true),
    DELETE("DELETE", new KeyFormat(), true, true),
    INCREMENT("INCREMENT", new KeyFormat(), true, true),
    EXISTS("EXISTS", new KeyFormat(), true, false),
    INDEX("INDEX", new KeyValueFormat(), true, false),
    SIZE("SIZE", new KeyFormat(), true, false),
    PUSH("PUSH", new KeyValueFormat(), true, true),
    POP("POP", new KeyFormat(), true, true),
    SHIFT("SHIFT", new KeyFormat(), true, false),
    UNSHIFT("UNSHIFT", new KeyValueFormat(), true, true),
    LOGIN("LOGIN", new KeyFormat(), false, false),
    USE("USE", new KeyFormat(), true, false),
    DB("DB", new PlainFormat(), true, false),
    EXIT("EXIT", new PlainFormat(), false, false);

    private final String name;

    private final Format format;

    private boolean loginRequired;

    private boolean isSerializable;

    private static final Map<String, CommandType> types = Arrays.stream(CommandType.values()).collect(
            Collectors.toMap(CommandType::getName, command -> command)
    );

    CommandType(final String name, final Format format, final boolean loginRequired, final boolean isSerializable) {
        this.name = name;
        this.format = format;
        this.loginRequired = loginRequired;
        this.isSerializable = isSerializable;
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

    public boolean isSerializable() {
        return isSerializable;
    }
}
