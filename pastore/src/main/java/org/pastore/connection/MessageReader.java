package org.pastore.connection;

import org.pastore.server.exception.ClientLeftException;
import org.pastore.server.exception.ServerException;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class MessageReader implements IReader {

    private static final int bufferSize = 100   ;

    private final SocketChannel channel;

    private StringBuilder unfinishedMessage;

    private ByteBuffer readBuffer;

    public MessageReader(final SocketChannel channel) throws IOException{
        this.channel = channel;
        this.unfinishedMessage = new StringBuilder();
        this.readBuffer = ByteBuffer.allocate(bufferSize);
    }

    public String[] readCommands() throws ServerException, IOException {
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
            throw new ClientLeftException("Client closed connection!");
        }
        return null;
    }

    private String[] parse(String newData) {
        if (newData.equals("\n")) {
            String[] messages = { unfinishedMessage.toString() };
            unfinishedMessage.setLength(0);
            return messages;
        }
        String[] messages = newData.split("\n", -1);
        if (messages.length > 0) {
            if (messages.length == 1) {
                unfinishedMessage.append(messages[0]);
            } else {
                if (! messages[0].isEmpty()) {
                    unfinishedMessage.append(messages[0]);
                }
                messages[0] = unfinishedMessage.toString();
                unfinishedMessage.setLength(0);
                if (! messages[messages.length - 1].isEmpty()) {
                    unfinishedMessage.append(messages[messages.length - 1]);
                }
                return copyMessages(messages);
            }
        }
        return null;
    }

    private String[] copyMessages(String[] messages) {
        return Arrays.copyOfRange(messages, 0, messages.length - 1);
    }

    public SocketChannel getChannel() {
        return channel;
    }

}