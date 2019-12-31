package io.github.linianhui.util;

import org.junit.jupiter.api.Test;

public class JsonAssertTest {
    @Test
    public void test_assertEquals_should_pass_when_equals() throws Exception {
        JsonAssert.assertEquals(
            "{\"id\":\"1\"}",
            "{\"id\":\"1\"}"
        );
        JsonAssert.assertEquals(
            "{\"id\":\"1\"}",
            "{\"id\":\"1\",\"name\":\"name\"}",
            "name"
        );
    }
}
