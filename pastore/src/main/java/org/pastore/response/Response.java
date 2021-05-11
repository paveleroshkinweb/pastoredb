package org.pastore.response;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class Response {

    private ResponseType responseType;

    private String response;

    private String prefix;

    public Response(final ResponseType responseType, final String response, final String prefix) {
        this.responseType = responseType;
        this.response = response;
        this.prefix = prefix;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public String getResponse() {
        return response;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public ByteBuffer toBuffer() {
        return ByteBuffer.wrap((this.prefix + this.response + "\r\n").getBytes(StandardCharsets.UTF_8));
    }

}
