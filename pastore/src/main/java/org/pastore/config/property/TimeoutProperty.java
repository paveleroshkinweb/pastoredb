package org.pastore.config.property;

import org.pastore.config.transform.IntegerTransform;

public class TimeoutProperty extends Property<Integer>{

    public TimeoutProperty() {
        super(ConfigProperty.TIMEOUT,
                0,
                new IntegerTransform());
    }
}
