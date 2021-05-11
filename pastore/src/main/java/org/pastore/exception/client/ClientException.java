package org.pastore.exception.client;

public class ClientException extends Exception {

    private static final String delimiter = "::";

    public ClientException(final String prefix, final int groupCode, final int code, final String msg) {
        super(String.join(delimiter, String.valueOf(groupCode), String.valueOf(code), prefix, msg));
    }
}
