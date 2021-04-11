package org.pastore.server;

import org.apache.log4j.Logger;
import org.pastore.client.Client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class UnencryptedServer extends Server {

    private static final Logger logger = Logger.getLogger(UnencryptedServer.class);

    private ServerSocketChannel channel;

    private Selector selector;

    private ServerSocket serverSocket;

    private Map<SocketChannel, Client> clients;

    private static final int chunkSize = 2048;

    public UnencryptedServer(ServerBuilder serverBuilder) throws IOException {
        super(serverBuilder);
        this.channel = ServerSocketChannel.open();
        this.serverSocket = this.channel.socket();
        this.serverSocket.setReuseAddress(true);
        this.serverSocket.bind(new InetSocketAddress(this.getBindAddress(), this.getPort()), this.getBacklog());
        this.channel.configureBlocking(false);
        this.selector = Selector.open();
        this.channel.register(selector, this.channel.validOps(), null);
        this.clients = new HashMap<>();
    }

    public void listen() {
        logger.info("Server listens " + this.getBindAddress() + ":" + this.getPort());
        try {
            while (this.channel.isOpen()) {
                this.selector.select();
                Set<SelectionKey> selectedKeys = this.selector.selectedKeys();
                Iterator<SelectionKey> iter = selectedKeys.iterator();
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    iter.remove();
                    try {
                        if (key.isAcceptable()) {
                            this.acceptNewClient(key);
                        } else if (key.isReadable()) {
                            this.readData(key);
                        } else if (key.isWritable()) {
                            this.writeData(key);
                        }
                    } catch (IOException e) {
                        logger.error("Unexpected error occurred", e);
                        key.cancel();
                        this.clients.remove(key.channel());
                        try {
                            key.channel().close();
                        } catch (IOException ex) {
                            logger.error("Can't close channel!", ex);
                        }
                    }
                }
            }
        } catch (IOException e) {
            logger.error("Unexpected error occurred", e);
        }
    }

    private void acceptNewClient(SelectionKey key) throws IOException {
        ServerSocketChannel server = (ServerSocketChannel) key.channel();
        SocketChannel clientChannel = server.accept();
        clientChannel.configureBlocking(false);
        clientChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        logger.info("New client " + clientChannel.getRemoteAddress() + " connected!");

        if (this.clients.size() < this.getMaxClients()) {
            this.clients.put(clientChannel, new Client(clientChannel));
        } else {
            ByteBuffer maxConnectionsMessage = ByteBuffer.wrap(
                "Can't accept new connection due to limited connection number".getBytes(StandardCharsets.UTF_8)
            );
            clientChannel.write(maxConnectionsMessage);
            clientChannel.close();
        }
    }

    private void readData(SelectionKey key) throws IOException{
        SocketChannel clientChannel = (SocketChannel) key.channel();
        StringBuilder command = new StringBuilder();
        ByteBuffer buffer = ByteBuffer.allocate(chunkSize);
        int read = 0;
        while ((read = clientChannel.read(buffer)) > 0) {
            buffer.flip();
            byte[] bytes = new byte[buffer.limit()];
            buffer.get(bytes);
            command.append(new String(bytes));
            buffer.clear();
        }
        if (read < 0) {
            System.out.println(this.clients);
            this.clients.remove(clientChannel);
            System.out.println(this.clients);
            logger.info("Client " + clientChannel.getRemoteAddress() + " closed connection");
            clientChannel.close();
        } else {
            this.clients.get(clientChannel).handle(command);
        }
    }

    private void writeData(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        if (key.attachment() != null) {
            ByteBuffer buffer = (ByteBuffer) key.attachment();
            if (! buffer.hasRemaining()) {
                buffer.rewind();
                int value = buffer.getInt();
                buffer.clear();
                buffer.putInt(value + 1);
                buffer.flip();
            }
            socketChannel.write(buffer);
        }
    }

    public void close() {
        System.out.println("I'm out!");
    }
}
