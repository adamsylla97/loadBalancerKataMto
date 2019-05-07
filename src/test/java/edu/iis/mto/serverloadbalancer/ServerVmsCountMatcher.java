package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ServerVmsCountMatcher extends TypeSafeMatcher<Server> {

    private int expectedVmsCounts;

    public ServerVmsCountMatcher(int expectedVmsCounts) {
        this.expectedVmsCounts = expectedVmsCounts;
    }

    @Override
    protected boolean matchesSafely(Server server) {
        return expectedVmsCounts == server.countVms();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a server with vms count of ").appendValue(expectedVmsCounts);
    }

    @Override
    protected void describeMismatchSafely(Server item, Description description) {
        description.appendText("a server with vms count of ").appendValue(item.countVms());
    }

    public static ServerVmsCountMatcher hasAVmsCountOf(int expectedVmsCounts) {
        return new ServerVmsCountMatcher(expectedVmsCounts);
    }
}
