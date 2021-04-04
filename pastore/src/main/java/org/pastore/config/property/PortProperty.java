package org.pastore.config.property;

import org.pastore.config.transform.PortTransform;

public class PortProperty extends Property<Integer> {

    public PortProperty() {
        super(ConfigProperty.PORT,
                8967,
                new PortTransform());
    }
}
