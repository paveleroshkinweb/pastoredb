package org.pastore.config.transform;

import org.pastore.config.exception.InvalidConfigPropertyException;
import org.pastore.config.property.ConfigProperty;
import org.pastore.utils.FSHelper;

public class LogFileTransform implements ITransform<String> {

    @Override
    public String transform(ConfigProperty property, String plainValue, String defaultValue)
            throws InvalidConfigPropertyException {

        String logfile = plainValue == null ? defaultValue : plainValue;
        FSHelper fsHelper = new FSHelper(logfile);
        if (fsHelper.createIfNotExist()) {
            return logfile;
        } else {
            throw new InvalidConfigPropertyException(property);
        }
    }
}
