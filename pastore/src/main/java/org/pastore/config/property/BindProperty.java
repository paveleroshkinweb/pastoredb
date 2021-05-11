package org.pastore.config.property;


import org.pastore.config.transform.AddressTransform;

public class BindProperty extends Property<String> {

    public BindProperty() {
        super(ConfigProperty.BIND,
                "localhost",
                new AddressTransform());
    }
}
