package org.pastore.exception.client.unknown;

import org.pastore.exception.client.ClientException;

public class UnknownFieldException extends ClientException {

    private static final int GROUP_CODE = 2;

    private static final String ERROR_MSG = "Unknown field";

    public UnknownFieldException(String description, int code) {
        super(ERROR_MSG, GROUP_CODE, code, description);
    }

}
