package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CurrentServerLoadMatcher extends TypeSafeMatcher<Server> {

    private double expectedServerLoadPercentage;
    private static final double EPSILON = 0.01d;

    public CurrentServerLoadMatcher(double expectedServerLoadPercentage) {
        this.expectedServerLoadPercentage = expectedServerLoadPercentage;
    }

    public static CurrentServerLoadMatcher hasCurrentLoadOf(double expectedServerLoadPercentage) {
        return new CurrentServerLoadMatcher(expectedServerLoadPercentage);
    }

    @Override protected boolean matchesSafely(Server server) {
        return doublesAreEqual(expectedServerLoadPercentage, server.currentLoadPercentage);
    }

    @Override protected void describeMismatchSafely(Server item, Description description) {
        description.appendText("current load of server: ").appendValue(item.currentLoadPercentage);
    }

    private boolean doublesAreEqual(double d1, double d2){
        return d1 == d2 || Math.abs(d1 - d2) < EPSILON;
    }

    @Override public void describeTo(Description description) {
        description.appendText("current load of server: ").appendValue(expectedServerLoadPercentage);
    }
}
