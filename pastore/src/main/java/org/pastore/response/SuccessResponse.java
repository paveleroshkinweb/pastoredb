package org.pastore.response;

public class SuccessResponse extends Response {

    public SuccessResponse(final String response) {
        super(ResponseType.SUCCESS, response);
    }

}
