package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CurrentServerLoadMatcher extends TypeSafeMatcher<Server> {

    private static final double EPSILON = 0.01d;
    private double expectedSercerLoadPercentage;

    public CurrentServerLoadMatcher(double expectedSercerLoadPercentage) {
        this.expectedSercerLoadPercentage = expectedSercerLoadPercentage;
    }

    @Override protected boolean matchesSafely(Server server) {
        return expectedSercerLoadPercentage == server.currentLoadPercentage ||
               Math.abs(expectedSercerLoadPercentage - server.currentLoadPercentage) < EPSILON;
    }

    @Override public void describeTo(Description description) {
        description.appendText("server load percentage: ").appendValue(expectedSercerLoadPercentage);
    }

    public static CurrentServerLoadMatcher hasCurrentLoadPercentageOf(double expectedSercerLoadPercentage) {
        return new CurrentServerLoadMatcher(expectedSercerLoadPercentage);
    }
}
