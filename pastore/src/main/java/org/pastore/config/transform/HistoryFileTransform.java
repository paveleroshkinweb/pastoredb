package org.pastore.config.transform;

import org.pastore.config.exception.InvalidConfigPropertyException;
import org.pastore.config.property.ConfigProperty;
import org.pastore.utils.FSHelper;

public class HistoryFileTransform implements ITransform<String>{

    @Override
    public String transform(ConfigProperty property, String plainValue, String defaultValue) throws InvalidConfigPropertyException {
        String historyfile = plainValue == null ? defaultValue : plainValue;
        FSHelper fsHelper = new FSHelper(historyfile);
        if (fsHelper.createIfNotExist()) {
            return historyfile;
        }
        throw new InvalidConfigPropertyException(property);
    }
}
