package edu.iis.mto.serverloadbalancer;

public class Server {

    public double currentLoadPercentage;
    public int capacity;
    private final double MAXIMUM_LOAD = 100.0d;

    public boolean contains(Vm theVm) {
        return true;
    }

    public Server(int capacity) {
        this.capacity = capacity;
    }

    public void addVm(Vm vm) {
        currentLoadPercentage = (double)vm.size / capacity * MAXIMUM_LOAD;
    }
}
