package org.pastore.command.format;

import org.pastore.command.option.OptionType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SetFormat extends KeyValueFormat {

    private static final Set<OptionType> reqOptions = new HashSet<>();

    private static final Set<OptionType> posOptions = new HashSet<>(
            Arrays.asList(new OptionType[] { OptionType.EXPIRES, OptionType.TYPE })
    );

    public SetFormat() {
        super(reqOptions, posOptions);
    }
}
