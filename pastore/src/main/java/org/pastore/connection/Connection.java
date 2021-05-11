package org.pastore.connection;

import org.pastore.config.property.PasswordProtectedProperty;
import org.pastore.response.Response;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class Connection {

    private final SocketChannel clientChannel;

    private final IReader messageReader;

    private boolean needToBeClosed;

    private boolean closed;

    private Selector selector;

    private ByteBuffer response;

    private boolean loggedIn;

    private int currentDB;

    private SocketAddress socketAddress;

    public Connection(final SocketChannel clientChannel,
                      final Selector selector,
                      final IReader messageReader,
                      final PasswordProtectedProperty passwordProtectedProperty) throws IOException {
        this.clientChannel = clientChannel;
        this.messageReader = messageReader;
        this.needToBeClosed = false;
        this.selector = selector;
        this.response = null;
        this.loggedIn = ! passwordProtectedProperty.getValue();
        this.socketAddress = clientChannel.getRemoteAddress();
        this.currentDB = 0;
        this.closed = false;
    }

    public boolean needToBeClosed() {
        return this.needToBeClosed;
    }

    public IReader getMessageReader() {
        return messageReader;
    }

    public void setResponse(Response response) throws IOException {
        this.response = response.toBuffer();
        this.clientChannel.register(this.selector, SelectionKey.OP_WRITE);
        this.selector.wakeup();
    }

    public void setResponseAndClose(Response response) throws IOException {
        this.needToBeClosed = true;
        this.setResponse(response);
    }

    public void closeConnection() throws IOException {
        this.setClosed();
        this.clientChannel.register(this.selector, SelectionKey.OP_WRITE);
        this.selector.wakeup();
    }

    public ByteBuffer getResponse() {
        return this.response;
    }

    public void setLoggedIn() {
        this.loggedIn = true;
    }

    public void setCurrentDB(int currentDB) {
        this.currentDB = currentDB;
    }

    public int getCurrentDB() {
        return currentDB;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed() {
        this.closed = true;
    }

    @Override
    public String toString() {
        return "Connection{" + "address=" + this.socketAddress + '}';
    }
}
