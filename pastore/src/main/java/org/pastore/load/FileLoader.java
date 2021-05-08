package org.pastore.load;

import org.pastore.cli.CLIOption;
import org.pastore.exception.load.LoadConfigException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class FileLoader extends AbstractLoader {

    private Properties properties;

    public FileLoader(Map<CLIOption, Object> options) {
        super(options);
    }

    @Override
    protected void load() {
        String filePath = (String) this.getOptions().get(CLIOption.CONFIG_PATH);
        if (filePath != null) {
            try {
                FileReader fileReader = new FileReader(filePath);
                this.properties = new Properties();
                this.properties.load(fileReader);
            } catch (IOException e) {
                throw new LoadConfigException("Can't read file " + filePath, e);
            }
        }
    }

    @Override
    public String getPlainPropertyValue(String key) {
        return this.properties.getProperty(key);
    }

}
