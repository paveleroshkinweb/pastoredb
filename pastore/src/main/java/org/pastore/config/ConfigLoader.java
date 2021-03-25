package org.pastore.config;

import org.pastore.config.exception.InvalidConfigFileException;
import org.pastore.utils.FSHelper;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {

    private static boolean configLoaded = false;

    private static Properties properties;

    private static String configPath;

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
