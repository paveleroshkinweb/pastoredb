package org.pastore.config.property;

import org.pastore.config.transform.IntegerTransform;

public class DatabasesProperty extends Property<Integer>{

    public DatabasesProperty() {
        super(ConfigProperty.DATABASES,
                10,
                new IntegerTransform());
    }
}
