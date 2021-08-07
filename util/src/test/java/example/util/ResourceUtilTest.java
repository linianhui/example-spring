package example.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ResourceUtilTest extends BaseTest {

    @Test
    void test_readFileAsUtf8_should_file_all_content_when_file_is_exists() throws Exception {
        final String text = getResourceUtil().readFileAsUtf8("/readFileAsUtf8.txt");
        Assertions.assertEquals("this is file content.", text);
    }
}
