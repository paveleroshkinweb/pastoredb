package org.pastore.exception.client.auth;

import org.pastore.exception.client.ClientException;

public class AuthException extends ClientException {

    private static final int GROUP_CODE = 4;

    private static final String ERROR_MSG = "Auth error";

    public AuthException(String description, int code) {
        super(ERROR_MSG, GROUP_CODE, code, description);
    }
}
