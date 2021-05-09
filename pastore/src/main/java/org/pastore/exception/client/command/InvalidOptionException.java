package org.pastore.exception.client.command;

import org.pastore.command.option.OptionType;

public class InvalidOptionException extends InvalidCommandException {

    private static final String MESSAGE = "%s is not a valid option for this command!";

    private static final int CODE = 3;

    public InvalidOptionException(OptionType optionType) {
        super(String.format(MESSAGE, optionType.toString()), CODE);
    }
}
