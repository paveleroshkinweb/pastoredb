package org.pastore.command.exception;

import org.pastore.command.option.OptionType;

public class InvalidOptionException extends InvalidCommandException {

    private static final String MESSAGE = "%s is not a valid option for this command!";

    public InvalidOptionException(OptionType optionType) {
        super(String.format(MESSAGE, optionType.toString()));
    }
}
