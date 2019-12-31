package io.github.linianhui.util;

import org.junit.jupiter.api.Test;

public class AssertUtilTest extends BaseTest {
    @Test
    public void test_assertJsonStringEquals_should_pass_when_equals() throws Exception {
        getAssertUtil().assertJsonStringEquals(
            "{\"id\":\"1\"}",
            "{\"id\":\"1\"}",
            null
        );
    }

    @Test
    public void test_assertJsonStringEquals_should_pass_when_equals_with_ignoreFieldRegexPath() throws Exception {
        getAssertUtil().assertJsonStringEquals(
            "{\"id\":\"1\"}",
            "{\"id\":\"1\",\"name\":\"name\"}",
            new String[]{"name"}
        );
    }

    @Test
    public void test_assertJsonFileEquals_should_pass_when_equals_with_ignoreFieldRegexPath() throws Exception {
        getAssertUtil().assertJsonFileEquals(
            "/assertJsonFileEquals.json",
            "{\"id\":\"1\",\"name\":\"name\"}",
            new String[]{"name"}
        );
    }
}
