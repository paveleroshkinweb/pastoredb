package org.pastore.parse;

import org.pastore.command.CommandType;
import org.pastore.command.option.OptionType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SetCommandParser extends KeyCommandParser {

    private static final Set<OptionType> requiredOptions = new HashSet<>(
            Arrays.asList(new OptionType[]{ OptionType.VALUE })
    );

    private static final Set<OptionType> additionalOptions = new HashSet<>(
            Arrays.asList(new OptionType[]{ OptionType.TYPE, OptionType.EXPIRES })
    );

    public SetCommandParser() {
        super(CommandType.SET, requiredOptions, additionalOptions);
    }
}
