package org.pastore.config.property;


import org.pastore.config.transform.BindTransform;

public class BindProperty extends Property<String> {

    public BindProperty() {
        super(ConfigProperty.BIND,
                "localhost",
                new BindTransform());
    }
}
