package org.pastore.config;

import org.apache.log4j.Logger;
import org.pastore.config.property.*;
import org.pastore.exception.config.ConfigLoadedException;
import org.pastore.exception.config.InvalidConfigPropertyException;
import org.pastore.logging.LoggerLoader;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Loader {

    private static final Logger logger = Logger.getLogger(Loader.class);

    private static final String CONFIG_LOADED_ERROR = "Config already loaded! You should call it only once!";

    private static final int CONFIG_IS_NOT_ACCESSIBLE_CODE_EXIT = 2;

    private static final int LOGGER_CONFIG_ISSUES_CODE_EXIT = 3;

    private static boolean isLoaded = false;

    private static Properties properties;

    private static String configPath;

    private Loader() {}

    private static Map<ConfigProperty, Property> instances = new HashMap<>();

    public static void load(final String path) {
        if (isLoaded) {
            throw new ConfigLoadedException(CONFIG_LOADED_ERROR);
        }
        properties = new Properties();
        if (path != null) {
            try {
                FileReader fileReader = new FileReader(path);
                properties.load(fileReader);
            } catch (IOException e) {
                logger.error(e);
                System.exit(CONFIG_IS_NOT_ACCESSIBLE_CODE_EXIT);
            }
        }
        loadLogger();
        loadRestProperties();
        configPath = path;
        isLoaded = true;
    }

    public static String getConfigPath() {
        return configPath;
    }

    public static final String getPlainPropertyValue(String key) {
        return properties.getProperty(key);
    }

    public static BacklogProperty getBacklogProperty() {
        return (BacklogProperty) instances.get(ConfigProperty.TCP_BACKLOG);
    }

    public static BindProperty getBindProperty() {
        return (BindProperty) instances.get(ConfigProperty.BIND);
    }

    public static DatabasesProperty getDatabasesProperty() {
        return (DatabasesProperty) instances.get(ConfigProperty.DATABASES);
    }

    public static DumpFileProperty getDumpFileProperty() {
        return (DumpFileProperty) instances.get(ConfigProperty.DUMPFILE);
    }

    public static HistoryFileProperty getHistoryFileProperty() {
        return (HistoryFileProperty) instances.get(ConfigProperty.HISTORYFILE);
    }

    public static MaxClientsProperty getMaxClientsProperty() {
        return (MaxClientsProperty) instances.get(ConfigProperty.MAX_CLIENTS);
    }

    public static PasswordProtectedProperty getPasswordProtectedProperty() {
        return (PasswordProtectedProperty) instances.get(ConfigProperty.PASSWORD_PROTECTED);
    }

    public static PortProperty getPortProperty() {
        return (PortProperty) instances.get(ConfigProperty.PORT);
    }

    public static SaveIntervalProperty getSaveIntervalProperty() {
        return (SaveIntervalProperty) instances.get(ConfigProperty.SAVE);
    }

    public static ServerTypeProperty getServerTypeProperty() {
        return (ServerTypeProperty) instances.get(ConfigProperty.SERVER_TYPE);
    }

    private static void put(Property property) throws InvalidConfigPropertyException {
        if (! instances.containsKey(property.getConfigProperty())) {
            instances.put(property.getConfigProperty(), property);
        }
    }

    private static void loadLogger() {
        LogFileProperty logFileProperty = new LogFileProperty();
        LogLevelProperty logLevelProperty = new LogLevelProperty();
        put(logFileProperty);
        put(logLevelProperty);
        try {
            LoggerLoader.loadLogger(logFileProperty, logLevelProperty);
        } catch (InvalidConfigPropertyException e) {
            logger.error(e);
            System.exit(LOGGER_CONFIG_ISSUES_CODE_EXIT);
        }
    }

    private static void loadRestProperties() {
        put(new BacklogProperty());
        put(new BindProperty());
        put(new DatabasesProperty());
        put(new DumpFileProperty());
        put(new HistoryFileProperty());
        put(new MaxClientsProperty());
        put(new PasswordProtectedProperty());
        put(new PortProperty());
        put(new SaveIntervalProperty());
        put(new ServerTypeProperty());
    }

}
