package io.github.linianhui.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ResourceUtilTest extends BaseTest {

    @Test
    public void test_readFileAsUtf8_should_file_all_content_when_file_is_exists() throws Exception {
        final String text = getResourceUtil().readFileAsUtf8("/readFileAsUtf8.txt");
        Assertions.assertEquals("this is file content.\n2", text);
    }
}
