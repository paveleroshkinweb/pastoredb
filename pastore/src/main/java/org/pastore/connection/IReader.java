package org.pastore.connection;

import org.pastore.exception.connection.ConnectionException;

import java.io.IOException;

public interface IReader {

    String readCommand() throws IOException, ConnectionException;

}
