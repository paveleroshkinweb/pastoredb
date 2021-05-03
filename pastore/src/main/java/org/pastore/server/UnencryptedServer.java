package org.pastore.server;

import org.apache.log4j.Logger;
import org.pastore.connection.Connection;
import org.pastore.connection.MessageReader;
import org.pastore.exception.connection.ConnectionException;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UnencryptedServer extends Server {

    private static final Logger logger = Logger.getLogger(UnencryptedServer.class);

    private ServerSocketChannel channel;

    private Selector selector;

    private ServerSocket serverSocket;

    private Map<SocketChannel, Connection> connections;

    private final ExecutorService workers = Executors.newSingleThreadExecutor();

    public UnencryptedServer(ServerBuilder serverBuilder) throws IOException {
        super(serverBuilder);
        this.channel = ServerSocketChannel.open();
        this.serverSocket = this.channel.socket();
        this.serverSocket.setReuseAddress(true);
        this.serverSocket.bind(new InetSocketAddress(this.getBindAddress().getValue(), this.getPort().getValue()), this.getBacklog().getValue());
        this.channel.configureBlocking(false);
        this.selector = Selector.open();
        this.channel.register(selector, SelectionKey.OP_ACCEPT);
        this.connections = new HashMap<>();
    }

    public void listen() {
        logger.info("Server listens " + this.getBindAddress().getValue() + ":" + this.getPort().getValue());
        try {
            while (this.channel.isOpen()) {
                this.selector.select();
                Set<SelectionKey> selectedKeys = this.selector.selectedKeys();
                for (Iterator<SelectionKey> it = selectedKeys.iterator(); it.hasNext();) {
                    SelectionKey key = it.next();
                    it.remove();
                    try {
                        if (key.isValid()) {
                            if (key.isAcceptable()) {
                                this.acceptNewClient(key);
                            } else if (key.isReadable()) {
                                this.readData(key);
                            } else if (key.isWritable()) {
                                this.writeData(key);
                            }
                        }
                    } catch (IOException e) {
                        logger.error("Unexpected IOException while processing key occurred", e);
                        key.cancel();
                        SocketChannel channel = (SocketChannel) key.channel();
                        this.closeConnection(channel);
                    }
                }
            }
        } catch (IOException e) {
            logger.error("Unexpected error occurred", e);
        }
    }

    private void acceptNewClient(SelectionKey key) throws IOException {
        ServerSocketChannel server = (ServerSocketChannel) key.channel();
        SocketChannel connectionChannel = server.accept();
        connectionChannel.configureBlocking(false);
        connectionChannel.register(key.selector(), SelectionKey.OP_READ);
        logger.info("New client " + connectionChannel.getRemoteAddress() + " connected!");

        MessageReader messageReader = new MessageReader(connectionChannel);
        Connection newConnection = new Connection(connectionChannel,
                                                  this.selector,
                                                  messageReader,
                                                  this.isPasswordProtected());
        this.connections.put(connectionChannel, newConnection);

        if (this.connections.size() > this.getMaxClients().getValue()) {
            newConnection.setErrorResponseAndClose("Can't accept new connection due to limited connection number");
        }
    }

    private void readData(SelectionKey key) throws IOException {
        SocketChannel connectionChannel = (SocketChannel) key.channel();
        Connection connection = this.connections.get(connectionChannel);
        MessageReader messageReader = connection.getMessageReader();
        try {
            String plainCommand = messageReader.readCommand();
            if (plainCommand != null) {
                logger.info("Recieved new commands from: " + connection);
                workers.execute(new Worker(connection, plainCommand));
            }
        } catch (ConnectionException e) {
            logger.info("Connection exception occurred while reading data: " + e.getMessage(), e);
            this.closeConnection(connectionChannel);
        }
    }

    private void writeData(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        Connection connection = this.connections.get(socketChannel);
        if (connection.getResponse() == null) {
            this.closeConnection(socketChannel);
            return;
        }
        socketChannel.write(connection.getResponse());
        if (! connection.getResponse().hasRemaining()) {
            socketChannel.register(this.selector, SelectionKey.OP_READ);
            if (connection.needToBeClosed()) {
                this.closeConnection(socketChannel);
            }
        }
    }

    private void closeChannel(SocketChannel channel) {
        if (channel.isOpen()) {
            try {
                SocketAddress remoteAddress = channel.getRemoteAddress();
                channel.close();
                logger.info("Successfully closed " + remoteAddress);
            } catch (IOException e) {
                logger.error("Unexpected IOException occurred while closing channel", e);
            }
        }
    }

    private void closeConnection(SocketChannel channel) {
        if (this.connections.containsKey(channel)) {
            this.connections.get(channel).setClosed();
            this.connections.remove(channel);
        }
        this.closeChannel(channel);
    }

    public void close() {
        this.connections.keySet().forEach(this::closeConnection);
        try {
            this.channel.close();
        } catch (IOException e) {
            logger.error("Unexpected IOException occurred while closing sever", e);
        }
    }
}
