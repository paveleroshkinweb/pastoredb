package org.pastore.command.format;

import org.pastore.command.PropertyType;
import org.pastore.command.option.OptionType;

import java.util.HashSet;
import java.util.Set;

public class PlainFormat extends Format {

    private static final PropertyType[] defaultFormat = {};

    private static final Set<OptionType> defaultReqOptions = new HashSet<>();

    private static final Set<OptionType> defaultPosOptions = new HashSet<>();

    public PlainFormat() {
        super(defaultFormat, defaultReqOptions, defaultPosOptions);
    }

    public PlainFormat(PropertyType[] format) {
        super(format, defaultReqOptions, defaultPosOptions);
    }

    public PlainFormat(PropertyType[] format, Set<OptionType> reqOptions, Set<OptionType> posOptions) {
        super(format, reqOptions, posOptions);
    }
}
