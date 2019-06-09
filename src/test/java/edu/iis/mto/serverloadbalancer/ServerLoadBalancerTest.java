package edu.iis.mto.serverloadbalancer;


import static edu.iis.mto.serverloadbalancer.CurrentServerLoadMatcher.*;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.*;
import static edu.iis.mto.serverloadbalancer.VmBuilder.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.rules.Timeout;

public class ServerLoadBalancerTest {

	@Test
	public void itCompiles() {
		assertThat(true, equalTo(true));
	}

	@Test
	public void balancingSerber_noVm_ServerStaysEmpty(){

		Server theServer = a(server().withCapacity(1));
		balancing(listOfServers(theServer), emptyListOfVm());
		assertThat(theServer, hasCurrentLoadOf(0.0d));

	}

	@Test
	public void balancingOneServerWithOneSlotCapacity_andOneSlotVm_fillsTheServerWithTheVm(){

		Server theServer = a(server().withCapacity(1));
		Vm theVm = a(vm().ofSize(1));
		balancing(listOfServers(theServer), listWithVms(theVm));

		assertThat(theServer, hasCurrentLoadOf(100.0d));
		assertThat("server should containt theVm", theServer.contains(theVm));

	}

	@Test
	public void balancingOneServerWithTenSlotsCapcity_andOneSlotVm_fillTheServerWithTenPercent(){

		Server theServer = a(server().withCapacity(10));
		Vm theVm = a(vm().ofSize(1));
		balancing(listOfServers(theServer), listWithVms(theVm));

		assertThat(theServer, hasCurrentLoadOf(10.0d));
		assertThat("server should containt theVm", theServer.contains(theVm));

	}

	@Test
	public void balancingAServerWithEnoughRoom_getsFilledWithAlldVms(){

		Server theServer = a(server().withCapacity(100));
		Vm theFirstVm = a(vm().ofSize(1));
		Vm theSecondVm = a(vm().ofSize(1));
		balancing(listOfServers(theServer), listWithVms(theFirstVm, theSecondVm));

		assertThat(theServer, hasCountOfVm(2));
		assertThat("server should contain first Vm", theServer.contains(theFirstVm));
		assertThat("server should contain second Vm", theServer.contains(theSecondVm));

	}

	private Matcher<? super Server> hasCountOfVm(int expectedCountOfVms) {
		return new CurrentServerVmsCountMatcher(expectedCountOfVms);
	}

	private Vm[] listWithVms(Vm... vms) {
		return vms;
	}

	private void balancing(Server[] servers, Vm[] vms) {
		new ServerLoadBalancer().balance(servers,vms);
	}

	private Vm[] emptyListOfVm() {
		return new Vm[0];
	}

	private Server[] listOfServers(Server... servers) {
		return servers;
	}

	private <T> T  a(Builder<T> builder){
		return builder.build();
	}

}
