package org.pastore.command;

import org.pastore.command.option.OptionType;

import java.util.Map;

public class Command {

    private CommandType commandType;

    private String plainCommand;

    private Map<PropertyType, String> properties;

    private Map<OptionType, String> options;

    public Command(CommandType commandType,
                   String plainCommand,
                   Map<PropertyType, String> properties,
                   Map<OptionType, String> options) {
        this.commandType = commandType;
        this.plainCommand = plainCommand;
        this.properties = properties;
        this.options = options;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public Map<PropertyType, String> getProperties() {
        return properties;
    }

    public Map<OptionType, String> getOptions() {
        return options;
    }

}
