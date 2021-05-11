package org.pastore.exception.client.required;

import org.pastore.exception.client.ClientException;

public class EmptyRequiredFieldException extends ClientException {

    private static final int GROUP_CODE = 1;

    private static final String ERROR_MSG = "Missed required fields";

    public EmptyRequiredFieldException(String description, int code) {
        super(ERROR_MSG, GROUP_CODE, code, description);
    }
}
