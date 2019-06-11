package edu.iis.mto.serverloadbalancer;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class ServerLoadBalancerTest {
	@Test
	public void itCompiles() {
		assertThat(true, equalTo(true));
	}

	FizzBuzz fizzBuzz = new FizzBuzz();

	@Test
	public void shouldReturnFizzWhenNumberIsDivisibleByThree(){

		String result = fizzBuzz.check(3);
		assertThat("result should contain Fizz: ",result.contains("Fizz"));

	}

	@Test
	public void shouldReturnBuzzWhenNumberIsDivibleByFize(){

		String result = fizzBuzz.check(5);
		assertThat("result should contain Buzz", result.contains("Buzz"));

	}

	@Test
	public void shouldReturnFizzBuzzWhenNumberIsDivisibleByFiveAndThree(){

		String result = fizzBuzz.check(15);
		assertThat("result should contain FizzBuzz", result.contains("FizzBuzz"));

	}

	@Test
	public void shouldReturnNumberWhenNumberIsNotDivisibleByFiveAndThree() throws Exception {
		// When
		String result = fizzBuzz.check(1);
		// Then
		assertThat("result should contain number",result.contains("1"));
	}
}
