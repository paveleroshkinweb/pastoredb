package org.pastore.exception.client.auth;

public class AuthWrongPasswordException extends AuthException {

    private static final String MESSAGE = "Invalid password!";

    private static final int CODE = 1;

    public AuthWrongPasswordException() {
        super(MESSAGE, CODE);
    }
}
