package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CurrentLoadPercentageMatcher extends TypeSafeMatcher<Server> {

    private static final double EPSILON = 0.01d;
    private double expectedServerLoadPercentage;

    public CurrentLoadPercentageMatcher(double expectedServerLoadPercentage) {
        this.expectedServerLoadPercentage = expectedServerLoadPercentage;
    }

    @Override protected boolean matchesSafely(Server server) {
        return doublesAreEqual(expectedServerLoadPercentage, server.currentLoadPercentage);
    }

    private boolean doublesAreEqual(double d1, double d2){
        return d1 == d2 || Math.abs(d1 - d2) < EPSILON;
    }

    @Override public void describeTo(Description description) {
        description.appendText("current server load percentage: ").appendValue(expectedServerLoadPercentage);
    }

    @Override protected void describeMismatchSafely(Server item, Description description) {
        description.appendText("current server load percentage: ").appendValue(item.currentLoadPercentage);
    }

    public static CurrentLoadPercentageMatcher hasExpectedLoadOf(double expectedServerLoadPercentage) {
        return new CurrentLoadPercentageMatcher(expectedServerLoadPercentage);
    }
}
