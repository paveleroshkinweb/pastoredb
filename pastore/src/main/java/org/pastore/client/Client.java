package org.pastore.client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Client implements IClient{

    private final SocketChannel clientChannel;

    public Client(final SocketChannel clientChannel) {
        this.clientChannel = clientChannel;
    }

    public void handle(StringBuilder command) throws IOException {
        System.out.println(command);
        ByteBuffer maxConnectionsMessage = ByteBuffer.wrap(
                command.toString().getBytes(StandardCharsets.UTF_8)
        );
        clientChannel.write(maxConnectionsMessage);
    }

    public SocketChannel getClientChannel() {
        return clientChannel;
    }
}
