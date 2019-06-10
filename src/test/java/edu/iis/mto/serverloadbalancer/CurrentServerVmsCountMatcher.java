package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CurrentServerVmsCountMatcher extends TypeSafeMatcher<Server> {

    private int expectedVmsCount;

    public CurrentServerVmsCountMatcher(int expectedVmsCount) {
        this.expectedVmsCount = expectedVmsCount;
    }

    @Override protected boolean matchesSafely(Server server) {
        return server.getVmsCount() == expectedVmsCount;
    }

    @Override public void describeTo(Description description) {
        description.appendText("current server count of vms: ").appendValue(expectedVmsCount);
    }

    @Override protected void describeMismatchSafely(Server item, Description description) {
        description.appendText("current server count of vms: ").appendValue(item.getVmsCount());
    }
}
