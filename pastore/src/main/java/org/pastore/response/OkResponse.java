package org.pastore.response;

public class OkResponse extends SuccessResponse {

    public OkResponse() {
        super(ResponseType.OK, "OK");
    }
}
