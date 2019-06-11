package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CurrentVmsCountMatcher extends TypeSafeMatcher<Server> {

    private int expectedVmsCount;

    public CurrentVmsCountMatcher(int expectedVmsCount) {
        this.expectedVmsCount = expectedVmsCount;
    }

    @Override protected boolean matchesSafely(Server server) {
        return expectedVmsCount == server.getVmsCount();
    }

    @Override public void describeTo(Description description) {
        description.appendText("server have current vms count of: ").appendValue(expectedVmsCount);
    }

    @Override protected void describeMismatchSafely(Server item, Description description) {
        description.appendText("server have current vms count of: ").appendValue(item.getVmsCount());
    }

    public static CurrentVmsCountMatcher hasVmCountOf(int expectedVmsCount) {
        return new CurrentVmsCountMatcher(expectedVmsCount);
    }
}
