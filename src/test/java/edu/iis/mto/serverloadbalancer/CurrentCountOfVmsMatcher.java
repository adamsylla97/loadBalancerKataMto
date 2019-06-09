package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CurrentCountOfVmsMatcher extends TypeSafeMatcher<Server> {

    private int expectedCountOfVms;

    public CurrentCountOfVmsMatcher(int expectedCountOfVms) {
        this.expectedCountOfVms = expectedCountOfVms;
    }

    @Override protected boolean matchesSafely(Server server) {
        return expectedCountOfVms == server.vms.size();
    }

    @Override public void describeTo(Description description) {
        description.appendText("actual server count of vms: ").appendValue(expectedCountOfVms);
    }

    @Override protected void describeMismatchSafely(Server item, Description description) {
        description.appendText("actual server count of vms: ").appendValue(item.vms.size());
    }

   public static CurrentCountOfVmsMatcher hasExpectedCountOf(int expectedCountOfVms) {
        return new CurrentCountOfVmsMatcher(expectedCountOfVms);
    }
}
