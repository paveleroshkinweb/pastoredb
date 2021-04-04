package org.pastore.server;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum ServerType {

    ENCRYPTED("encrypted"),

    UNENCRYPTED("unencrypted");

    private final String name;

    private static final Map<String, ServerType> types = new HashMap<>(){
        {
            put(ServerType.UNENCRYPTED.getName(), ServerType.UNENCRYPTED);
            put(ServerType.ENCRYPTED.getName(), ServerType.ENCRYPTED);
        }
    };

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
