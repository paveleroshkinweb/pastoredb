package org.pastore.config;

import org.apache.log4j.Logger;
import org.pastore.config.property.*;
import org.pastore.exception.config.InvalidConfigPropertyException;
import org.pastore.logging.LoggerLoader;
import org.pastore.utils.FSHelper;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Loader {

    private static boolean loaded = false;

    private static Properties properties;

    private static String configPath;

    private Loader() {}

    private static Map<ConfigProperty, Property> instances = new HashMap<>();

    public static void load(final String path) throws IOException {
        if (loaded) {
            return;
        }
        properties = new Properties();
        if (path != null) {
            FSHelper fsHelper = new FSHelper(path);
            if (! fsHelper.isFileAccessible()) {
                System.exit(2);
            }
            FileReader fileReader = new FileReader(path);
            properties.load(fileReader);
        }
        loadLogger();
        loadRestProperties();
        configPath = path;
        loaded = true;
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

    private static void process(Property property) throws InvalidConfigPropertyException {
        if (! instances.containsKey(property.getConfigProperty())) {
            property.process();
            instances.put(property.getConfigProperty(), property);
        }
    }

    private static void loadLogger() {
        LogFileProperty logFileProperty = new LogFileProperty();
        LogLevelProperty logLevelProperty = new LogLevelProperty();
        try {
            process(logLevelProperty);
            process(logFileProperty);
            LoggerLoader.loadLogger(logFileProperty, logLevelProperty);
        } catch (InvalidConfigPropertyException e) {
            System.exit(3);
        }
    }

    private static void loadRestProperties() {
        Property[] properties = {
                new BacklogProperty(),
                new BindProperty(),
                new DatabasesProperty(),
                new DumpFileProperty(),
                new HistoryFileProperty(),
                new MaxClientsProperty(),
                new PasswordProtectedProperty(),
                new PortProperty(),
                new SaveIntervalProperty(),
                new ServerTypeProperty()
        };
        Logger logger = Logger.getLogger(Loader.class);
        for (Property property : properties) {
            try {
                process(property);
            } catch (InvalidConfigPropertyException e) {
                logger.error(e.getMessage(), e);
                System.exit(4);
            }
        }
    }

}
