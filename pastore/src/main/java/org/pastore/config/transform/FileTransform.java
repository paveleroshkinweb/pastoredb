package org.pastore.config.transform;

import org.pastore.exception.config.InvalidConfigPropertyException;
import org.pastore.config.property.ConfigProperty;
import org.pastore.utils.FSHelper;

public class FileTransform implements ITransform<String> {

    private static final String ERROR = "Please make sure that %s exists and you can access it!";

    @Override
    public String transform(ConfigProperty property, String plainValue, String defaultValue) throws InvalidConfigPropertyException {
        String historyfile = plainValue == null ? defaultValue : plainValue;
        FSHelper fsHelper = new FSHelper(historyfile);
        if (fsHelper.createIfNotExist()) {
            return historyfile;
        }
        throw new InvalidConfigPropertyException(String.format(ERROR, property.getPropertyName()));
    }
}
