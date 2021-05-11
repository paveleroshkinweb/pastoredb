package org.pastore.db.value;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum DBValueType {

    STRING("S"),
    INTEGER("I"),
    LIST_STR("LS"),
    LIST_INT("LI"),
    NULL("NULL");

    private final String prefix;

    private static final Map<String, DBValueType> types = Arrays.stream(DBValueType.values()).collect(
            Collectors.toMap(DBValueType::getPrefix, type -> type)
    );

    DBValueType(final String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    public static DBValueType getTypeByName(String name, DBValueType defaultValue) {
        return types.getOrDefault(name, defaultValue);
    }
}
