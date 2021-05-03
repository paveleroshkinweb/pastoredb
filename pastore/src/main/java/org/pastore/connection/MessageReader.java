package org.pastore.connection;

import org.pastore.clientexception.connection.ClientLeftException;
import org.pastore.clientexception.connection.ConnectionException;
import org.pastore.clientexception.connection.InvalidProtocolException;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;


public class MessageReader implements IReader {

    private static final String PROTOCOL_ERR = "Client has to wait an answer from pastore before send next request using one connection";

    private static final String CLIENT_LEFT_ERR = "Client closed connection!";

    private static final int bufferSize = 8192;

    private static final String delimiter = "\n";

    private final SocketChannel channel;

    private StringBuilder unfinishedMessage;

    private ByteBuffer readBuffer;

    public MessageReader(final SocketChannel channel) throws IOException {
        this.channel = channel;
        this.unfinishedMessage = new StringBuilder();
        this.readBuffer = ByteBuffer.allocate(bufferSize);
    }

    public String readCommand() throws ConnectionException, IOException {
        int read = 0;
        while ((read = channel.read(this.readBuffer)) > 0) {
            this.readBuffer.flip();
            byte[] bytes = new byte[this.readBuffer.limit()];
            this.readBuffer.get(bytes);
            this.readBuffer.clear();
            String newData = new String(bytes, StandardCharsets.UTF_8);
            return this.parse(newData);
        }

        if (read == -1) {
            throw new ClientLeftException(CLIENT_LEFT_ERR);
        }
        return null;
    }

    private String parse(String newData) throws ConnectionException {
        int delimiterIndex = newData.indexOf(delimiter);
        if (delimiterIndex == -1) {
            unfinishedMessage.append(newData);
            return null;
        }
        if (delimiterIndex < newData.length() - 1) {
            throw new InvalidProtocolException(PROTOCOL_ERR);
        }
        String result = unfinishedMessage.append(newData, 0, newData.length() - 1).toString();
        unfinishedMessage.setLength(0);
        return result;
    }

    public SocketChannel getChannel() {
        return channel;
    }

}
