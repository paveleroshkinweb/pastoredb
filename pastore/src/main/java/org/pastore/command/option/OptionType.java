package org.pastore.command.option;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum OptionType {

    EXPIRES("EXPIRES"),
    VALUE("VALUE"),
    TYPE("TYPE");

    final String name;

    private static final Map<String, OptionType> types = Arrays.stream(OptionType.values()).collect(
            Collectors.toMap(OptionType::getName, type -> type)
    );

    OptionType(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static OptionType getTypeByName(String name) {
        return types.get(name);
    }

    @Override
    public String toString() {
        return this.getName();
    }

}
