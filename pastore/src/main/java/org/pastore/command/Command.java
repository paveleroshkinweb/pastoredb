package org.pastore.command;

import org.pastore.command.option.OptionType;

import java.io.Serializable;
import java.util.Map;

public class Command implements Serializable {

    private CommandType commandType;

    private transient String plainCommand;

    private Map<PropertyType, String> properties;

    private Map<OptionType, String> options;

    private int storeNumber;

    public Command(CommandType commandType,
                   String plainCommand,
                   Map<PropertyType, String> properties,
                   Map<OptionType, String> options) {
        this.commandType = commandType;
        this.plainCommand = plainCommand;
        this.properties = properties;
        this.options = options;
    }

    public int getStoreNumber() {
        return storeNumber;
    }

    public void setStoreNumber(int storeNumber) {
        this.storeNumber = storeNumber;
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
