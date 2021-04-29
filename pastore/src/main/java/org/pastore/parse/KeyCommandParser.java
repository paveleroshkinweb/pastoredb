package org.pastore.parse;

import org.pastore.command.Command;
import org.pastore.command.CommandType;
import org.pastore.command.exception.InvalidCommandException;
import org.pastore.command.option.OptionType;

import java.util.Map;
import java.util.Set;

public class KeyCommandParser extends Parser {

    public KeyCommandParser(CommandType commandType, Set<OptionType> requiredOptions, Set<OptionType> additionalOptions) {
        super(commandType, requiredOptions, additionalOptions);
    }

    public Command parse(String plainValue) throws InvalidCommandException {
        String property = this.extractProperty(plainValue);
        Map<OptionType, String> options = this.extractOptions(plainValue, 2);
        Command command = new Command(this.getCommandType(), plainValue, property, options);
        System.out.println(command);
        return command;
    }
}
