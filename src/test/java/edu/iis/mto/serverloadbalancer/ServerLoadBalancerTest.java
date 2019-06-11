package edu.iis.mto.serverloadbalancer;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class ServerLoadBalancerTest {
	@Test
	public void itCompiles() {
		assertThat(true, equalTo(true));
	}


	@Test
	public void shouldReturnFizzWhenNumberIsDivisibleByThree(){

		FizzBuzz fizzBuzz = new FizzBuzz();
		String result = fizzBuzz.check(3);
		assertThat("result should contain Fizz: ",result.contains("Fizz"));

	}

}
