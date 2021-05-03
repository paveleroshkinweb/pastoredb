package org.pastore.command.format;

import org.pastore.command.PropertyType;
import org.pastore.command.option.OptionType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PushFormat extends Format {

    private static final PropertyType[] format = { PropertyType.KEY, PropertyType.VALUE };

    private static final Set<OptionType> reqOptions = new HashSet<>();

    private static final Set<OptionType> posOptions = new HashSet<>(
            Arrays.asList(new OptionType[] { OptionType.EXPIRES, OptionType.TYPE })
    );

    public PushFormat() {
        super(format, reqOptions, posOptions);
    }
}
