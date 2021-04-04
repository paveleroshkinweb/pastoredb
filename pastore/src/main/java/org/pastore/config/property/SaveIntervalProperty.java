package org.pastore.config.property;

import org.pastore.config.transform.IntegerTransform;

public class SaveIntervalProperty extends Property<Integer>{

    public SaveIntervalProperty() {
        super(ConfigProperty.SAVE,
                600,
                new IntegerTransform());
    }
}
