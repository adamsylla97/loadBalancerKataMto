package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class CurrentLoadMatcher extends TypeSafeMatcher<Server> {

    private double expectedLoadPercentage;
    private final double EPSILON = 0.01d;

    public CurrentLoadMatcher(double expectedLoadPercentage) {

        this.expectedLoadPercentage = expectedLoadPercentage;
    }

    @Override protected boolean matchesSafely(Server server) {
        return doublesAreEqual(expectedLoadPercentage, server.currentLoadPercentage);
    }

    private boolean doublesAreEqual(double d1, double d2){
        return d1 == d2 || Math.abs(d1 - d2 ) < EPSILON;
    }

    @Override public void describeTo(Description description) {
        description.appendText("a server with load percentage of ").appendValue(expectedLoadPercentage);
    }

    public static CurrentLoadMatcher hasCurrentLoadOf(double expectedLoadPercentage) {
        return new CurrentLoadMatcher(expectedLoadPercentage);
    }
}
