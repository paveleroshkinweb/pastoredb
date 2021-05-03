package org.pastore.command.format;

import org.pastore.command.PropertyType;
import org.pastore.command.option.OptionType;

import java.util.HashSet;
import java.util.Set;

public class IncrementFormat extends Format {

    private static final PropertyType[] format = { PropertyType.KEY };

    private static final Set<OptionType> reqOptions = new HashSet<>();

    private static final Set<OptionType> posOptions = new HashSet<>();

    public IncrementFormat() {
        super(format, reqOptions, posOptions);
    }
}
