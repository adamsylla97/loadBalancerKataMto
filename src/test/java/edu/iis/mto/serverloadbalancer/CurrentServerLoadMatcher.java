package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CurrentServerLoadMatcher extends TypeSafeMatcher<Server> {

    private double expectedSercerLoadPercentage;

    public CurrentServerLoadMatcher(double expectedSercerLoadPercentage) {
        this.expectedSercerLoadPercentage = expectedSercerLoadPercentage;
    }

    @Override protected boolean matchesSafely(Server server) {
        return expectedSercerLoadPercentage == server.currentLoadPercentage ||
               Math.abs(expectedSercerLoadPercentage - server.currentLoadPercentage) < 0.01d;
    }

    @Override public void describeTo(Description description) {
        description.appendText("server load percentage: ").appendValue(expectedSercerLoadPercentage);
    }
}
