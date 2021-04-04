package org.pastore.config.property;

public enum ConfigProperty {

    SERVER_TYPE("server-type"),
    MAX_CLIENTS("maxclients"),
    DUMPFILE("dumpfile"),
    LOGFILE("logfile"),
    HISTORYFILE("historyfile"),
    BIND("bind"),
    PORT("port"),
    PASSWORD_PROTECTED("password-protected"),
    TIMEOUT("timeout"),
    TCP_BACKLOG("tcp-backlog"),
    LOG_LEVEL("loglevel"),
    DATABASES("databases"),
    SAVE("save");

    private final String propertyName;

    ConfigProperty(final String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyName() {
        return this.propertyName;
    }

//    MAX_CLIENTS("maxclients", 1500),
//    DUMPFILE("dumpfile", "/var/lib/pastore/pastore-dump.pdb"),
//    HISTORYFILE("historyfile", "/var/lib/pastore/pastore-history.phist"),
//    BIND("bind", "127.0.0.1"),
//    PORT("port", 8967),
//    PASSWORD_PROTECTED("password-protected", false),
//    TIMEOUT("timeout", 0),
//    TCP_BACKLOG("tcp-backlog", 500),
//    DATABASES("databases", 10),
//    SAVE("save", 600);
}
