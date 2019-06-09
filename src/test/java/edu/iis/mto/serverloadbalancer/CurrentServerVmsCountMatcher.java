package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CurrentServerVmsCountMatcher extends TypeSafeMatcher<Server> {

    private int expectedCountOfVms;

    public CurrentServerVmsCountMatcher(int expectedCountOfVms) {
        this.expectedCountOfVms = expectedCountOfVms;
    }

    @Override protected boolean matchesSafely(Server server) {
        return expectedCountOfVms == server.countVms();
    }

    @Override protected void describeMismatchSafely(Server item, Description description) {
        description.appendText("server should contain: ").appendValue(item.countVms());
    }

    @Override public void describeTo(Description description) {
        description.appendText("server should contain: ").appendValue(expectedCountOfVms);
    }

    public static CurrentServerVmsCountMatcher hasCountOfVm(int expectedCountOfVms) {
        return new CurrentServerVmsCountMatcher(expectedCountOfVms);
    }
}
