package org.pastore.config.transform;

import org.pastore.config.exception.InvalidConfigPropertyException;
import org.pastore.config.property.ConfigProperty;
import org.pastore.utils.FSHelper;

public class DumpFileTransform implements ITransform<String>{

    @Override
    public String transform(ConfigProperty property, String plainValue, String defaultValue) throws InvalidConfigPropertyException {
        String dumpfile = plainValue == null ? defaultValue : plainValue;
        FSHelper fsHelper = new FSHelper(dumpfile);
        if (fsHelper.createIfNotExist()) {
            return dumpfile;
        }
        throw new InvalidConfigPropertyException(property);
    }
}
