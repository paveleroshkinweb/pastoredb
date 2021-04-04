package org.pastore.config.property;

import org.pastore.config.transform.PasswordProtectedTransform;

public class PasswordProtectedProperty extends Property<Boolean> {

    public PasswordProtectedProperty() {
        super(ConfigProperty.PASSWORD_PROTECTED,
                false,
                new PasswordProtectedTransform());
    }
}
