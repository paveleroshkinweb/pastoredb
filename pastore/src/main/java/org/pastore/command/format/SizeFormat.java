package org.pastore.command.format;

import org.pastore.command.PropertyType;
import org.pastore.command.option.OptionType;

import java.util.HashSet;
import java.util.Set;

public class SizeFormat extends Format {

    private static final PropertyType[] format = { PropertyType.KEY };

    private static final Set<OptionType> reqOptions = new HashSet<>();

    private static final Set<OptionType> posOptions = new HashSet<>();

    public SizeFormat() {
        super(format, reqOptions, posOptions);
    }
}