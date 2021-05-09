package org.pastore.response;

public class Response {

    private ResponseType responseType;

    private String response;

    public Response(final ResponseType responseType, final String response) {
        this.responseType = responseType;
        this.response = response;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public String getResponse() {
        return response;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
