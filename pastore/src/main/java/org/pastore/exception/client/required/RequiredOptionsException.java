package org.pastore.exception.client.required;

import org.pastore.command.option.OptionType;

import java.util.Set;

public class RequiredOptionsException extends EmptyRequiredFieldException {

    private static final String MESSAGE = "Missed required options: %s";

    private static final int CODE = 1;

    public RequiredOptionsException(Set<OptionType> missedOptions) {
        super(String.format(MESSAGE, missedOptions.toString()), CODE);
    }
}
