package example.util;

import org.junit.jupiter.api.Test;

class AssertUtilTest extends BaseTest {
    @Test
    void test_assertJsonStringEquals_should_pass_when_equals() throws Exception {
        getAssertUtil().assertJsonStringEquals(
            "{\"id\":\"1\"}",
            "{\"id\":\"1\"}",
            null
        );
    }

    @Test
    void test_assertJsonStringEquals_should_pass_when_equals_with_ignoreFieldRegexPath() throws Exception {
        getAssertUtil().assertJsonStringEquals(
            "{\"id\":\"1\"}",
            "{\"id\":\"1\",\"name\":\"name\"}",
            new String[]{"name"}
        );
    }

    @Test
    void test_assertJsonFileEquals_should_pass_when_equals_with_ignoreFieldRegexPath() throws Exception {
        getAssertUtil().assertJsonFileEquals(
            "/assertJsonFileEquals.json",
            "{\"id\":\"1\",\"name\":\"name\"}",
            new String[]{"name"}
        );
    }
}
