package org.pastore.server;

public class UnencryptedServer extends Server {

    public UnencryptedServer(ServerBuilder serverBuilder) {
        super(serverBuilder);
    }

    public void listen() {
        System.out.println("Here go");
    }

    public void close() {
        System.out.println("I'm out!");
    }
}
