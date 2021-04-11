package org.pastore.client;

import java.io.IOException;

public interface IClient {

    void handle(StringBuilder command) throws IOException;
}
