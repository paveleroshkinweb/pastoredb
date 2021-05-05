package org.pastore.command.format;

import org.pastore.command.PropertyType;


public class KeyFormat extends PlainFormat {

    private static final PropertyType[] format = { PropertyType.KEY };

    public KeyFormat() {
        super(format);
    }

}
