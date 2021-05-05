package org.pastore.command.format;

import org.pastore.command.PropertyType;
import org.pastore.command.option.OptionType;

import java.util.Set;

public class KeyValueFormat extends PlainFormat {

    private static final PropertyType[] format = { PropertyType.KEY, PropertyType.VALUE };

    public KeyValueFormat() {
        super(format);
    }

    public KeyValueFormat(Set<OptionType> reqOptions, Set<OptionType> posOptions) {
        super(format, reqOptions, posOptions);
    }
}
