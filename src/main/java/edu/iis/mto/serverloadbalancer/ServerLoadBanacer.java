package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

public class ServerLoadBanacer {

    public void balance(Server[] servers, Vm[] vms) {
        if (vms.length > 0){
            for(Vm vm: vms){
                addVmToLessLoadedServer(servers,vm);
            }
        }
    }

    private void addVmToLessLoadedServer(Server[] servers, Vm vm) {
        List<Server> capableServers = new ArrayList<>();
        for(Server server: servers){
            if(server.canFit(vm)){
                capableServers.add(server);
            }
        }
        Server lessLoadedServer = findLessLoadedServer(capableServers);
        if (lessLoadedServer != null){
            lessLoadedServer.addVm(vm);
        }
    }

    private Server findLessLoadedServer(List<Server> servers) {
        Server lessLoadedServer = null;
        for(Server server: servers){
            if(lessLoadedServer == null || server.currentLoadPercentage < lessLoadedServer.currentLoadPercentage){
                lessLoadedServer = server;
            }
        }
        return lessLoadedServer;
    }
}
