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

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public Map<PropertyType, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<PropertyType, String> properties) {
        this.properties = properties;
    }

    public Map<OptionType, String> getOptions() {
        return options;
    }

    public void setOptions(Map<OptionType, String> options) {
        this.options = options;
    }

    public String getPlainCommand() {
        return plainCommand;
    }

    public void setPlainCommand(String plainCommand) {
        this.plainCommand = plainCommand;
    }
}
