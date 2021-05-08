package org.pastore.config;

import org.apache.log4j.Logger;
import org.pastore.config.property.ConfigProperty;
import org.pastore.config.property.LogFileProperty;
import org.pastore.config.property.LogLevelProperty;
import org.pastore.config.property.Property;
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
        configPath = path;
        isLoaded = true;
    }

    public static String getConfigPath() {
        return configPath;
    }

    public static final String getPlainPropertyValue(String key) {
        return properties.getProperty(key);
    }

    private static void loadLogger() {
        try {
            LogFileProperty logFileProperty = new LogFileProperty();
            LogLevelProperty logLevelProperty = new LogLevelProperty();
            LoggerLoader.loadLogger(logFileProperty, logLevelProperty);
        } catch (InvalidConfigPropertyException e) {
            logger.error(e);
            System.exit(LOGGER_CONFIG_ISSUES_CODE_EXIT);
        }
    }

}
