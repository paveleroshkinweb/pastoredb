package org.pastore.exception.client.auth;

public class AuthRequiredException extends AuthException {

    private static final String MESSAGE = "Pastore has been started in password-protected mode, please login!";

    private static final int CODE = 0;

    public AuthRequiredException(){
        super(MESSAGE, CODE);
    }

}
