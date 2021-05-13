package org.pastore.command;

import org.pastore.command.option.OptionType;

import java.io.Serializable;
import java.util.*;

public class Command implements Serializable {

    private static final OptionType[] OPTIONS_BLACK_LIST = { OptionType.EXPIRES };

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

    public Command(CommandType commandType,
                   String plainCommand,
                   Map<PropertyType, String> properties,
                   Map<OptionType, String> options,
                   int storeNumber) {
        this.commandType = commandType;
        this.plainCommand = plainCommand;
        this.properties = properties;
        this.options = options;
        this.storeNumber = storeNumber;
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

    public Command toHistory() {
        Command commandToHistory = new Command(this.commandType,
                                               this.plainCommand,
                                               new HashMap<>(this.properties),
                                               new HashMap<>(this.options),
                                               this.storeNumber);
        for (OptionType optionType : OPTIONS_BLACK_LIST) {
            if (commandToHistory.getOptions() != null) {
                if (commandToHistory.getOptions().get(optionType) != null) {
                    commandToHistory.getOptions().remove(optionType);
                }
            }
        }
        return commandToHistory;

    }

}
