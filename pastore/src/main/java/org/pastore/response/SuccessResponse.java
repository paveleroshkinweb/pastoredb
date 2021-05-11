package org.pastore.response;

public class SuccessResponse extends Response {

    public SuccessResponse(final String response) {
        super(ResponseType.SUCCESS, response, "+");
    }

    public SuccessResponse(final ResponseType responseType, final String response) {
        super(responseType, response, "+");
    }

}
