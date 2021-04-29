package org.pastore.command.exception;

import org.pastore.command.option.OptionType;

import java.util.Set;

public class RequiredOptionsException extends InvalidCommandException {

    private static final String PREFIX = "Missed required options";

    public RequiredOptionsException(Set<OptionType> missedOptions) {
        super(PREFIX, missedOptions.toString());
    }
}
