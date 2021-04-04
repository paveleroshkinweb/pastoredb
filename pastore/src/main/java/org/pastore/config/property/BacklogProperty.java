package org.pastore.config.property;

import org.pastore.config.transform.IntegerTransform;

public class BacklogProperty extends Property<Integer>{

    public BacklogProperty() {
        super(ConfigProperty.TCP_BACKLOG,
                300,
                new IntegerTransform());
    }
}
