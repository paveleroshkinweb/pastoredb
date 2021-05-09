package org.pastore.response;

public class FailResponse extends Response {

    public FailResponse(String response) {
        super(ResponseType.FAIL, response);
    }
}
