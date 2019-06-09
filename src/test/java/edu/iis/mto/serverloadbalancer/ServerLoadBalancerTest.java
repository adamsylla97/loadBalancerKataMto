package edu.iis.mto.serverloadbalancer;


import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentageMatcher.*;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.*;
import static edu.iis.mto.serverloadbalancer.VmBuilder.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

public class ServerLoadBalancerTest {

	@Test
	public void itCompiles() {
		assertThat(true, equalTo(true));
	}

	@Test
	public void balancingServer_noVm_ServerStaysEmpty(){

		Server theServer = a(server().withCapacity(1));
		balancing(aListOfServers(theServer), anEmptyListOfVms());
		assertThat(theServer, hasExpectedLoadOf(0.0d));

	}

	@Test
	public void balancingOneServerWithOneSlotCapacity_andOneSlotVm_fillsTheServerWithTheVM(){

		Server theServer = a(server().withCapacity(1));
		Vm theVm = a(vm().ofSize(1));

		balancing(aListOfServers(theServer), listWithVms(theVm));
		assertThat("server should contain the vm", theServer.contains(theVm));
		assertThat(theServer, hasExpectedLoadOf(100.0d));

	}

	private Vm[] listWithVms(Vm... vms) {
		return vms;
	}

	private void balancing(Server[] servers, Vm[] vms) {
		new ServerLoadBalancer().balance(servers,vms);
	}

	private Vm[] anEmptyListOfVms() {
		return new Vm[0];
	}

	private Server[] aListOfServers(Server... servers) {
		return servers;
	}

	private <T> T a(Builder<T> builder){
		return builder.build();
	}

}
