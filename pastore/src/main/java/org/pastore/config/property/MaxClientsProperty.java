package org.pastore.config.property;

import org.pastore.config.transform.MaxClientsTransform;

public class MaxClientsProperty extends Property<Integer>{

    public MaxClientsProperty() {
        super(ConfigProperty.MAX_CLIENTS,
                1500,
                new MaxClientsTransform());
    }
}
