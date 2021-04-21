package org.pastore.connection;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Connection {

    private final SocketChannel clientChannel;

    private final MessageReader messageReader;

    private boolean needToBeClosed;

    private Selector selector;

    private ByteBuffer response;

    public Connection(final SocketChannel clientChannel,
                      final Selector selector,
                      final MessageReader messageReader) throws IOException {
        this.clientChannel = clientChannel;
        this.messageReader = messageReader;
        this.needToBeClosed = false;
        this.selector = selector;
        this.response = null;
    }

    public void setToBeClosed() {
        this.needToBeClosed = true;
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

    public void setResponse(String response) throws IOException{
        this.response = ByteBuffer.wrap(response.getBytes(StandardCharsets.UTF_8));
        this.clientChannel.register(this.selector, SelectionKey.OP_WRITE);
    }

    public ByteBuffer getResponse() {
        return this.response;
    }


}
