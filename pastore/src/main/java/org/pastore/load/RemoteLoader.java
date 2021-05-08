package org.pastore.load;

import org.pastore.cli.CLIOption;
import org.pastore.exception.load.LoadConfigException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

public class RemoteLoader extends AbstractLoader {

    private Properties properties;

    public RemoteLoader(Map<CLIOption, Object> options) {
        super(options);
    }

    @Override
    protected void load() {
        String url = (String) this.getOptions().get(CLIOption.CONFIG_PATH);
        try {
            BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
            this.properties = new Properties();
            properties.load(in);
        } catch (IOException e) {
            throw new LoadConfigException("Can't read file " + url, e);
        }
    }

    @Override
    public String getPlainPropertyValue(String key) {
        return this.properties.getProperty(key);
    }
}
