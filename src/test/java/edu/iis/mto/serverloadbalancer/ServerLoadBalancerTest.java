package edu.iis.mto.serverloadbalancer;


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
		balancing(listWithServer(theServer),anEmptyListOfVms());
		assertThat(theServer, hasCurrentLoadPercentageOf(0.0d));

	}

	private Matcher<? super Server> hasCurrentLoadPercentageOf(double expectedSercerLoadPercentage) {
		return new CurrentServerLoadMatcher(expectedSercerLoadPercentage);
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

	private Server a(ServerBuilder builder) {
		return builder.build();
	}

	private ServerBuilder server() {
		return new ServerBuilder();
	}

}
