package io.github.linianhui.springexample.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.DefaultComparator;
import org.springframework.test.web.reactive.server.EntityExchangeResult;

@Getter
@AllArgsConstructor(staticName = "of")
public class AssertUtil {
    private final ResourceUtil resourceUtil;

    public Consumer<EntityExchangeResult<byte[]>> assertJsonFileEquals(
        final String expectedJsonFile,
        final String[] ignoreFieldRegexPath
    ) {
        return actualJsonBytes -> {
            try {
                assertJsonFileEquals(expectedJsonFile, actualJsonBytes, ignoreFieldRegexPath);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        };
    }

    private void assertJsonFileEquals(
        final String expectedJsonFile,
        final EntityExchangeResult<byte[]> actualJsonBytes,
        final String[] ignoreFieldRegexPath
    ) throws IOException, JSONException {
        final String actualJsonString = new String(actualJsonBytes.getResponseBody(), StandardCharsets.UTF_8);
        assertJsonFileEquals(expectedJsonFile, actualJsonString, ignoreFieldRegexPath);
    }

    public void assertJsonFileEquals(
        final String expectedJsonFile,
        final String actualJsonString,
        final String[] ignoreFieldRegexPath
    ) throws IOException, JSONException {
        final String expectedJson = this.getResourceUtil().readFileAsUtf8(expectedJsonFile);
        assertJsonStringEquals(expectedJson, actualJsonString, ignoreFieldRegexPath);
    }

    public void assertJsonStringEquals(
        final String expectedJsonString,
        final String actualJsonString,
        final String[] ignoreFieldRegexPath
    ) throws JSONException {
        final DefaultComparator customComparator = new JsonComparator(
            JSONCompareMode.STRICT_ORDER, ignoreFieldRegexPath
        );
        JSONAssert.assertEquals(expectedJsonString, actualJsonString, customComparator);
    }
}
