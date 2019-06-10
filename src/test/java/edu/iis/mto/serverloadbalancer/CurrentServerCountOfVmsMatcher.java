package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CurrentServerCountOfVmsMatcher extends TypeSafeMatcher<Server> {

    private int expectedVmsCount;

    public CurrentServerCountOfVmsMatcher(int expectedVmsCount) {
        this.expectedVmsCount = expectedVmsCount;
    }

    @Override
    protected boolean matchesSafely(Server server) {
        return expectedVmsCount == server.getVmsCount();
    }

    @Override
    protected void describeMismatchSafely(Server item, Description description) {
        description.appendText("vms count that server actually contains: ").appendValue(item.getVmsCount());
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("vms count that server actually contains: ").appendValue(expectedVmsCount);
    }

    public static CurrentServerCountOfVmsMatcher hasVmCountOf(int expectedVmsCount) {
        return new CurrentServerCountOfVmsMatcher(expectedVmsCount);
    }
}
