package org.pastore.server;

public enum ServerType {

    ENCRYPTED("encrypted"),

    UNENCRYPTED("unencrypted");

    private final String name;

    private ServerType(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
