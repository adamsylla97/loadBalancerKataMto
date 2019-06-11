package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class CurrentServerLoadMatcher extends TypeSafeMatcher<Server> {

    private static final double EPSILON = 0.01d;
    private double expectedServerLoadPercentage;

    public CurrentServerLoadMatcher(double expectedServerLoadPercentage) {
        this.expectedServerLoadPercentage = expectedServerLoadPercentage;
    }

    @Override protected boolean matchesSafely(Server server) {
        return doublesAreEqual(expectedServerLoadPercentage, server.getCurrentLoadPercentage());
    }

    private boolean doublesAreEqual(double d1, double d2){
        return d1 == d2 || Math.abs(d1 - d2) < EPSILON;
    }

    @Override public void describeTo(Description description) {
        description.appendText("current server load percentage: ").appendValue(expectedServerLoadPercentage);
    }

    public static CurrentServerLoadMatcher hasCurrentLoadPercentageOf(double expectedServerLoadPercentage) {
        return new CurrentServerLoadMatcher(expectedServerLoadPercentage);
    }
}
