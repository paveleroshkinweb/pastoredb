package org.pastore.config.property;

import java.util.HashMap;
import java.util.Map;

public class PropertyFactory {

    private static final Map<ConfigProperty, Property> instances = new HashMap<>() {
        {
            put(ConfigProperty.LOG_LEVEL, new LogLevelProperty());
            put(ConfigProperty.LOGFILE, new LogFileProperty());
            put(ConfigProperty.SERVER_TYPE, new ServerTypeProperty());
            put(ConfigProperty.BIND, new BindProperty());
            put(ConfigProperty.PORT, new PortProperty());
            put(ConfigProperty.MAX_CLIENTS, new MaxClientsProperty());
            put(ConfigProperty.DUMPFILE, new DumpFileProperty());
            put(ConfigProperty.HISTORYFILE, new HistoryFileProperty());
            put(ConfigProperty.PASSWORD_PROTECTED, new PasswordProtectedProperty());
            put(ConfigProperty.TIMEOUT, new TimeoutProperty());
            put(ConfigProperty.TCP_BACKLOG, new BacklogProperty());
            put(ConfigProperty.DATABASES, new DatabasesProperty());
            put(ConfigProperty.SAVE, new SaveIntervalProperty());
        }
    };

    private PropertyFactory() {}

    public static Property getProperty(ConfigProperty property) {
        return instances.get(property);
    }
}
