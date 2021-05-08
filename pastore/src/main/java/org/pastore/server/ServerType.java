package org.pastore.server;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public enum ServerType {

    ENCRYPTED("encrypted"),

    UNENCRYPTED("unencrypted");

    private final String name;

    private static final Map<String, ServerType> types = Arrays.stream(ServerType.values()).collect(
            Collectors.toMap(ServerType::getName, type -> type)
    );

    ServerType(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Set<String> availableServerTypes() {
        return types.keySet();
    }

    public static ServerType getServerTypeByName(String name) {
        return types.get(name);
    }
}
