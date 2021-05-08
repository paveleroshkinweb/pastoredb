package org.pastore.load;

import org.pastore.cli.CLIOption;

import java.util.Map;

public abstract class AbstractLoader {

    private final Map<CLIOption, Object> options;

    public AbstractLoader(Map<CLIOption, Object> options) {
        this.options = options;
        this.load();
    }

    public Map<CLIOption, Object> getOptions() {
        return options;
    }

    protected abstract void load();

    public abstract String getPlainPropertyValue(String key);
}
