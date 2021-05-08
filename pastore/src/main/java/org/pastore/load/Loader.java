package org.pastore.load;

import org.apache.log4j.Logger;
import org.pastore.cli.CLIOption;
import org.pastore.common.ExitCodes;
import org.pastore.config.property.LogFileProperty;
import org.pastore.config.property.LogLevelProperty;
import org.pastore.exception.config.ConfigLoadedException;
import org.pastore.exception.load.LoadConfigException;
import org.pastore.logging.LoggerLoader;

import java.util.Map;

public class Loader {

    private static final Logger logger = Logger.getLogger(Loader.class);

    private static final String CONFIG_LOADED_ERROR = "Config already loaded! You should call it only once!";

    private static AbstractLoader loader = null;

    private static LoaderType loaderType = null;

    private static boolean isLoaded = false;

    private Loader() {}

    public static void load(Map<CLIOption, Object> options) {
        if (isLoaded) {
            throw new ConfigLoadedException(CONFIG_LOADED_ERROR);
        }
        loaderType = (LoaderType) options.get(CLIOption.LOAD_TYPE);
        try {
            if (loaderType == LoaderType.FILE) {
                loader = new FileLoader(options);
            } else if (loaderType == LoaderType.REMOTE) {
                loader = new RemoteLoader(options);
            }
        } catch (LoadConfigException e) {
            logger.error(e);
            System.exit(ExitCodes.CONFIG_IS_NOT_ACCESSIBLE);
        }
        loadLogger();
        isLoaded = true;
    }

    public static final String getPlainPropertyValue(String key) {
        return loader.getPlainPropertyValue(key);
    }

    private static void loadLogger() {
        try {
            LogFileProperty logFileProperty = new LogFileProperty();
            LogLevelProperty logLevelProperty = new LogLevelProperty();
            LoggerLoader.loadLogger(logFileProperty, logLevelProperty);
        } catch (Exception e) {
            logger.error(e);
            System.exit(ExitCodes.LOGGER_CONFIG_ISSUES);
        }
    }

}
