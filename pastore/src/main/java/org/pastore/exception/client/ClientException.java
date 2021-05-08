package org.pastore.exception.client;

public class ClientException  extends Exception {

    private static final String delimiter = "::";

    public ClientException(final String prefix, final int code, final String msg) {
        super(String.join(delimiter, prefix, String.valueOf(code), msg));
    }
}
