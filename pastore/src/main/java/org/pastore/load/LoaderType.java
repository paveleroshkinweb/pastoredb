package org.pastore.load;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public enum LoaderType {

    FILE("file"),
    REMOTE("remote");

    private final String name;

    private static final Map<String, LoaderType> types = Arrays.stream(LoaderType.values()).collect(
            Collectors.toMap(LoaderType::getName, type -> type)
    );

    LoaderType(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static LoaderType getLoaderTypeByName(String name) {
        return types.get(name);
    }

    public static Set<String> availableLoaders() {
        return types.keySet();
    }
}
