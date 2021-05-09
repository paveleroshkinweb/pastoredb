package org.pastore.exception.client.command;

import org.pastore.command.option.OptionType;

import java.util.Set;

public class RequiredOptionsException extends InvalidCommandException {

    private static final String MESSAGE = "Missed required options: %s";

    private static final int CODE = 4;

    public RequiredOptionsException(Set<OptionType> missedOptions) {
        super(String.format(MESSAGE, missedOptions.toString()), CODE);
    }
}
