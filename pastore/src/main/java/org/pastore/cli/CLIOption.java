package org.pastore.cli;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public enum CLIOption {

    LOAD_TYPE("loadType"),

    CONFIG_PATH("configPath");

    private final String optionName;

    CLIOption(final String optionName) {
        this.optionName = optionName;
    }

    private static final Map<String, CLIOption> options = Arrays.stream(CLIOption.values()).collect(
            Collectors.toMap(CLIOption::getOptionName, option -> option)
    );

    public String getOptionName() {
        return optionName;
    }

    public static Set<String> availableCLIArgs() {
        return options.keySet();
    }
}
