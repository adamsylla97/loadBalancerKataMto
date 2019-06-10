package edu.iis.mto.serverloadbalancer;


import static edu.iis.mto.serverloadbalancer.CurrentServerLoadMatcher.*;
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
	public void balancingServer_noVm_ServerStaysEmpty(){

		Server theServer = a(server().withCapacity(1));
		balancing(listWithServers(theServer), anEmptyListOfVms());
		assertThat(theServer, hasCurrentLoadPercentage(0.0d));

	}

	@Test
	public void balancingOneServerWithOneSlotCapacity_andOneSlotVm_fillsTheServerWithTheVm(){

		Server theServer = a(server().withCapacity(1));
		Vm theVm = a(vm().ofSize(1));

		balancing(listWithServers(theServer), listWithVms(theVm));

		assertThat(theServer, hasCurrentLoadPercentage(100.0d));
		assertThat("server should contain the vm", theServer.contains(theVm));

	}

	@Test
	public void balancingOneServerWithTenSlotsCapacity_andOneSlotVm_fillsTheServerWithTenPercent(){

		Server theServer = a(server().withCapacity(10));
		Vm theVm = a(vm().ofSize(1));

		balancing(listWithServers(theServer), listWithVms(theVm));

		assertThat(theServer, hasCurrentLoadPercentage(10.0d));
		assertThat("server should contain the vm", theServer.contains(theVm));

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

	private Server[] listWithServers(Server... servers) {
		return servers;
	}

	private <T> T a(Builder<T> builder){
		return builder.build();
	}

}
