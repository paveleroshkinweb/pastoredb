package org.pastore.parse;

import org.pastore.command.Command;
import org.pastore.command.CommandType;
import org.pastore.command.exception.InvalidCommandException;
import org.pastore.command.option.OptionType;

import java.util.Set;

public class PlainCommandParser extends Parser {

    public PlainCommandParser(CommandType commandType, Set<OptionType> reqOptions, Set<OptionType> availableOptions) {
        super(commandType, reqOptions, availableOptions);
    }

    public Command parse(String plainValue) throws InvalidCommandException {
        return null;
    }
}
