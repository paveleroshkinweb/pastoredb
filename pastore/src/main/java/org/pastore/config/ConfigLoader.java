package org.pastore.config;

import org.pastore.config.exception.InvalidConfigFileException;
import org.pastore.config.property.*;
import org.pastore.utils.FSHelper;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigLoader {

    private static boolean configLoaded = false;

    private static Properties properties;

    private static String configPath;

    private static final Map<ConfigProperty, Property> instances = new HashMap<>();
//        {
//            put(ConfigProperty.LOG_LEVEL, new LogLevelProperty());
//            put(ConfigProperty.LOGFILE, new LogFileProperty());
//            put(ConfigProperty.SERVER_TYPE, new ServerTypeProperty());
//            put(ConfigProperty.BIND, new BindProperty());
//            put(ConfigProperty.PORT, new PortProperty());
//            put(ConfigProperty.MAX_CLIENTS, new MaxClientsProperty());
//            put(ConfigProperty.DUMPFILE, new DumpFileProperty());
//            put(ConfigProperty.HISTORYFILE, new HistoryFileProperty());
//            put(ConfigProperty.PASSWORD_PROTECTED, new PasswordProtectedProperty());
//            put(ConfigProperty.TIMEOUT, new TimeoutProperty());
//            put(ConfigProperty.TCP_BACKLOG, new BacklogProperty());
//            put(ConfigProperty.DATABASES, new DatabasesProperty());
//            put(ConfigProperty.SAVE, new SaveIntervalProperty());
//        }

    private ConfigLoader() {}

    public static void loadConfig(final String path) throws IOException, InvalidConfigFileException {
        if (configLoaded) {
            return;
        }
        FSHelper fsHelper = new FSHelper(path);
        if (!fsHelper.isFileAccessible()) {
            throw new InvalidConfigFileException();
        }
        FileReader fileReader = new FileReader(path);
        properties = new Properties();
        properties.load(fileReader);
        configPath = path;
        configLoaded = true;
    }

    public static String getProperty(String property) {
        return properties.getProperty(property);
    }

    public static String getConfigPath() {
        return configPath;
    }
}
