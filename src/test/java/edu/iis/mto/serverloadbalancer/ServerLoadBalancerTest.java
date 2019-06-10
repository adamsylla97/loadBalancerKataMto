package edu.iis.mto.serverloadbalancer;


import static edu.iis.mto.serverloadbalancer.CurrentServerLoadMatcher.*;
import static edu.iis.mto.serverloadbalancer.CurrentServerVmsCountMatcher.*;
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
	public void balancingServer_noVm_ServerStaysEmpty(){

		Server theServer = a(server().withCapacity(1));
		balancing(listWithServer(theServer),anEmptyListOfVms());
		assertThat(theServer, hasCurrentLoadPercentageOf(0.0d));

	}

	@Test
	public void balancingOneServerWithOneSlotCapacity_andOneSlotVm_fillsTheServerWithTheVm(){

		Server theServer = a(server().withCapacity(1));
		Vm theVm = a(vm().ofSize(1));

		balancing(listWithServer(theServer),listWithVms(theVm));

		assertThat(theServer, hasCurrentLoadPercentageOf(100.0d));
		assertThat("server should contain the vm", theServer.contains(theVm));

	}

	@Test
	public void balancingOneServerWithTenSlotsCapacity_andOneSlotVm_fillTheServerWithTenPercent(){

		Server theServer = a(server().withCapacity(10));
		Vm theVm = a(vm().ofSize(1));

		balancing(listWithServer(theServer),listWithVms(theVm));

		assertThat(theServer, hasCurrentLoadPercentageOf(10.0d));
		assertThat("server should contain the vm", theServer.contains(theVm));

	}

	@Test
	public void balancingAServerWithEnoughRoom_getsFilledWithAllVms(){

		Server theServer = a(server().withCapacity(100));
		Vm theFirstVm = a(vm().ofSize(1));
		Vm theSecondVm = a(vm().ofSize(1));

		balancing(listWithServer(theServer),listWithVms(theFirstVm, theSecondVm));

		assertThat(theServer, hasVmCountOf(2));
		assertThat("server should contain first vm", theServer.contains(theFirstVm));
		assertThat("server should contain second vm", theServer.contains(theSecondVm));

	}

	@Test
	public void aVm_shouldBeBalanced_onLessLoadedServerFirst(){

		Server moreLoadedServer = a(server().withCapacity(100).withCurrentLoad(50.0d));
		Server lessLoadedServer = a(server().withCapacity(100).withCurrentLoad(45.0d));
		Vm theFirstVm = a(vm().ofSize(10));

		balancing(listWithServer(moreLoadedServer,lessLoadedServer),listWithVms(theFirstVm));

		assertThat("less loaded server should contain the vm", lessLoadedServer.contains(theFirstVm));
		assertThat("more loaded server should not contain the vm", !moreLoadedServer.contains(theFirstVm));

	}

	@Test
	public void balancingServerWithNotEnoughRook_shouldNotBeFilledWithTheVm(){
		Server theServer = a(server().withCapacity(10).withCurrentLoad(90.0d));
		Vm theVm = a(vm().ofSize(2));

		balancing(listWithServer(theServer),listWithVms(theVm));
		assertThat("server should not contain the vm", !theServer.contains(theVm));
	}

	private Vm[] listWithVms(Vm... vms) {
		return vms;
	}

	private void balancing(Server[] servers, Vm[] vms) {
		new ServerLoadBanacer().balance(servers, vms);
	}

	private Vm[] anEmptyListOfVms() {
		return new Vm[0];
	}

	private Server[] listWithServer(Server... servers) {
		return servers;
	}

	private <T> T a(Builder<T> builder){
		return builder.build();
	}

}
