package org.pastore.connection;

import org.pastore.config.property.PasswordProtectedProperty;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Connection {

    private final SocketChannel clientChannel;

    private final MessageReader messageReader;

    private boolean needToBeClosed;

    private boolean closed;

    private Selector selector;

    private ByteBuffer response;

    private boolean loggedIn;

    private int currentDB;

    private SocketAddress socketAddress;

    public Connection(final SocketChannel clientChannel,
                      final Selector selector,
                      final MessageReader messageReader,
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

    public SocketChannel getClientChannel() {
        return clientChannel;
    }

    public MessageReader getMessageReader() {
        return messageReader;
    }

    private void setResponse(String response) throws IOException {
        if (response == null) {
            this.response = null;
        } else {
            this.response = ByteBuffer.wrap((response + "\n").getBytes(StandardCharsets.UTF_8));
        }
        this.clientChannel.register(this.selector, SelectionKey.OP_WRITE);
        this.selector.wakeup();
    }

    public void setOKResponse() throws IOException{
        this.setResponse("+OK");
    }

    public void setErrorResponse(String response) throws IOException {
        this.setResponse("-" + response);
    }

    public void setSuccessResponse(String response) throws IOException {
        this.setResponse("+" + response);
    }

    public void setErrorResponseAndClose(String response) throws IOException {
        this.needToBeClosed = true;
        this.setErrorResponse(response);
    }

    public void setSuccessResponseAndClose(String response) throws IOException {
        this.needToBeClosed = true;
        this.setSuccessResponse(response);
    }

    public void closeConnection() throws IOException {
        this.setResponse(null);
        this.setClosed();
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
