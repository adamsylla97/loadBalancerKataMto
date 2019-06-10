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
        return doublesAreEqual(expectedSercerLoadPercentage, server.currentLoadPercentage);
    }

    private boolean doublesAreEqual(double d1, double d2) {
        return d1 == d2 || Math.abs(d1 - d2 ) < EPSILON;
    }

    @Override public void describeTo(Description description) {
        description.appendText("server load percentage: ").appendValue(expectedSercerLoadPercentage);
    }

    @Override protected void describeMismatchSafely(Server item, Description description) {
        description.appendText("server load percentage: ").appendValue(item.currentLoadPercentage);
    }

    public static CurrentServerLoadMatcher hasCurrentLoadPercentageOf(double expectedSercerLoadPercentage) {
        return new CurrentServerLoadMatcher(expectedSercerLoadPercentage);
    }
}
