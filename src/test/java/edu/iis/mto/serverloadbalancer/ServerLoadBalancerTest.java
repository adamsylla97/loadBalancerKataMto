package edu.iis.mto.serverloadbalancer;


import static edu.iis.mto.serverloadbalancer.CurrentLoadMatcher.*;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.*;
import static edu.iis.mto.serverloadbalancer.VmBuilder.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matcher;
import org.junit.Test;

public class ServerLoadBalancerTest {
	@Test
	public void itCompiles() {
		assertThat(true, equalTo(true));
	}

	@Test
	public void balancingServerWithNoVms_serwerStaysEmpty(){

		Server theServer = a(server().withCapacity(1));
		balancing(aServerListWith(theServer), anEmptyListOfVms());
		assertThat(theServer, hasCurrentLoadOf(0.0d));

	}

	@Test
	public void balancingOneServerWithOneVms_serverIsFull(){

		Server theServer = a(server().withCapacity(1));
		Vm theVm = a(vm().ofSize(1));
		balancing(aServerListWith(theServer), aVmsListWith(theVm));

		assertThat(theServer, hasCurrentLoadOf(100.0d));
		assertThat("server should containt the vm", theServer.contains(theVm));

	}

	private Vm[] aVmsListWith(Vm... vms) {
		return vms;
	}

	private void balancing(Server[] servers, Vm[] vms) {
		new ServerLoadBalancer().balance(servers, vms);
	}

	private Vm[] anEmptyListOfVms() {
		return new Vm[0];
	}

	private Server[] aServerListWith(Server... servers) {
		return servers;
	}

	private <T> T  a(Builder<T> builder){
		return builder.build();
	}

}
