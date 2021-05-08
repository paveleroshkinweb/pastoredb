package org.pastore.exception.client.command;

import org.pastore.command.option.OptionType;

public class ExtraOptionException extends InvalidCommandException {

    private static final String MESSAGE = "%s extra usage";

    public ExtraOptionException(OptionType optionType) {
        super(String.format(MESSAGE, optionType.getName()));
    }
}
