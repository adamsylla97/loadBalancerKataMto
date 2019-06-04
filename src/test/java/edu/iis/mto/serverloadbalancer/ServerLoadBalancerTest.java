package edu.iis.mto.serverloadbalancer;


import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentageMatcher.*;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.*;
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
	public void balancingServerWithNoVms_serverStaysEmpty(){

		Server theServer = a(server().withCapacity(1));
		balancing(aServerListWith(theServer), anEmptyListOfVms());
		assertThat(theServer, hasCurrentLoadOf(0.0d));

	}

	@Test
	public void balancingOneServerWithOneVms_VmTakesAllOfServer(){

		Server theServer = a(server().withCapacity(1));
		Vm theVm = a(vm().ofSize(1));
		balancing(aServerListWith(theServer), aListOfVms(theVm));

		assertThat(theServer, hasCurrentLoadOf(100.0d));
		assertThat("server should contain the vm", theServer.contains(theVm));

	}

	private Vm[] aListOfVms(Vm... vms) {
		return vms;
	}

	private Vm a(VmBuilder builder){
		return builder.build();
	}

	private VmBuilder vm() {
		return new VmBuilder();
	}

	private void balancing(Server[] servers, Vm[] vms) {
		new ServerLoadBalancer().balance(servers,vms);
	}

	private Server[] aServerListWith(Server... servers) {
		return servers;
	}

	private Server a(ServerBuilder builder) {
		return build();
	}

	private Vm[] anEmptyListOfVms() {
		return new Vm[0];
	}

}
