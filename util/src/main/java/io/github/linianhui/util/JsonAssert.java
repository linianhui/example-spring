package io.github.linianhui.util;

import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.DefaultComparator;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

public class JsonAssert {
    public static void assertEquals(
        final String expectedJson,
        final String actualJson,
        final String... ignoreFieldRegexPath
    ) throws JSONException {
        DefaultComparator customComparator = new JsonComparator(
            JSONCompareMode.STRICT_ORDER, ignoreFieldRegexPath
        );
        JSONAssert.assertEquals(expectedJson, actualJson, customComparator);
    }

    public static Consumer<EntityExchangeResult<byte[]>> assertEqualsJsonFile(
        final String expectedJsonFile,
        final String... ignoreFieldRegexPath
    ) {
        return actualJsonBytes -> {
            try {
                assertEqualsJsonFile(expectedJsonFile, actualJsonBytes, ignoreFieldRegexPath);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        };
    }

    private static void assertEqualsJsonFile(
        final String expectedJsonFile,
        final EntityExchangeResult<byte[]> actualJsonBytes,
        final String... ignoreFieldRegexPath
    ) throws IOException, JSONException {
        String actualJson = new String(actualJsonBytes.getResponseBody(), StandardCharsets.UTF_8);
        String expectedJson = FileUtils.readFileToString(ResourceUtils.getFile(expectedJsonFile), StandardCharsets.UTF_8);
        assertEquals(expectedJson, actualJson, ignoreFieldRegexPath);
    }
}
