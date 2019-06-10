package edu.iis.mto.serverloadbalancer;

public class ServerLoadBanacer {

    public void balance(Server[] servers, Vm[] vms) {
        if (vms.length > 0){
            for(Vm vm: vms){
                Server lessLoadedServer = null;
                for(Server server: servers){
                    if(lessLoadedServer == null || server.currentLoadPercentage < lessLoadedServer.currentLoadPercentage){
                        lessLoadedServer = server;
                    }
                }
                lessLoadedServer.addVm(vm);
            }
        }
    }
}
