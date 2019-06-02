package edu.iis.mto.serverloadbalancer;

public class ServerBuilder {

    private int capacity;

    public static Server build() {
        return new Server();
    }

    public ServerBuilder withCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public static ServerBuilder server() {
        return new ServerBuilder();
    }
}
