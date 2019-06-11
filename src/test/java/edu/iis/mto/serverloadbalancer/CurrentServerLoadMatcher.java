package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CurrentServerLoadMatcher extends TypeSafeMatcher<Server> {

    private double expectedServerLoadPercentage;

    public CurrentServerLoadMatcher(double expectedServerLoadPercentage) {
        this.expectedServerLoadPercentage = expectedServerLoadPercentage;
    }

    @Override protected boolean matchesSafely(Server server) {
        return expectedServerLoadPercentage == server.currentLoadPercentage ||
               Math.abs(expectedServerLoadPercentage - server.currentLoadPercentage) < 0.01d;
    }

    @Override public void describeTo(Description description) {
        description.appendText("current server load percentage: ").appendValue(expectedServerLoadPercentage);
    }
}
