package edu.iis.mto.serverloadbalancer;

public class ServerLoadBalancer {

    public void balance(Server[] servers, Vm[] vms) {
        if(vms.length > 0){
            for(Vm vm: vms){
               addVmToLessLoadedServer(servers, vm);
            }
        }
    }

    private void addVmToLessLoadedServer(Server[] servers, Vm vm) {
        Server lessLoadedServer = findLessLoadedServer(servers,vm);
        lessLoadedServer.addVm(vm);
    }

    private Server findLessLoadedServer(Server[] servers, Vm vm) {
        Server lessLoadedServer = null;
        for(Server server: servers){
            if(lessLoadedServer == null || server.currentLoadPercentage < lessLoadedServer.currentLoadPercentage){
                lessLoadedServer = server;
            }
        }
        return lessLoadedServer;
    }
}
