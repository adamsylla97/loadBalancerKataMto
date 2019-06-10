package edu.iis.mto.serverloadbalancer;

public class ServerLoadBanacer {

    public void balance(Server[] servers, Vm[] vms) {
        if (vms.length > 0){
            for(Vm vm: vms){
                servers[0].addVm(vm);
            }
        }
    }
}
