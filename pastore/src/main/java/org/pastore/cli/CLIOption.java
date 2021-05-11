package org.pastore.cli;

public enum CLIOption {

    LOAD_TYPE("loadType"),

    CONFIG_PATH("configPath");

    private final String optionName;

    CLIOption(final String optionName) {
        this.optionName = optionName;
    }

    public String getOptionName() {
        return optionName;
    }
}
