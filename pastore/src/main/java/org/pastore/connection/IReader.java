package org.pastore.connection;

import org.pastore.server.exception.ServerException;

import java.io.IOException;

public interface IReader {

    String[] readCommands() throws ServerException, IOException;

}
