package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

public class Server {

    public static final double MAXIMUM_LOAD = 100.0d;
    public double currentLoadPercentage;
    public int capacity;
    private List<Vm> vms = new ArrayList<>();

    public boolean contains(Vm theVm) {
        return vms.contains(theVm);
    }

    public Server(int capacity) {
        this.capacity = capacity;
    }

    public void addVm(Vm vm) {
        currentLoadPercentage = loadOfVm(vm);
        vms.add(vm);
    }

    private double loadOfVm(Vm vm){
        return ((double)vm.size / (double)this.capacity * MAXIMUM_LOAD);
    }

    public int getVmsCount() {
        return vms.size();
    }

    public boolean canFit(Vm vm) {
        return currentLoadPercentage + loadOfVm(vm) <= MAXIMUM_LOAD;
    }
}
