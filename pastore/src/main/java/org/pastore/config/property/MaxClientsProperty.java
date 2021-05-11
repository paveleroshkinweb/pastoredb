package org.pastore.config.property;

import org.pastore.config.transform.IntegerTransform;

public class MaxClientsProperty extends Property<Integer>{

    public MaxClientsProperty() {
        super(ConfigProperty.MAX_CLIENTS,
                1500,
                new IntegerTransform());
    }
}
