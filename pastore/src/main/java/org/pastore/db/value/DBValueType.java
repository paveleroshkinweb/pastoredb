package org.pastore.db.value;

public enum DBValueType {

    STRING("S"),
    INTEGER("I"),
    LIST_STR("LS"),
    LIST_INT("LI");

    private final String prefix;

    DBValueType(final String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }
}
