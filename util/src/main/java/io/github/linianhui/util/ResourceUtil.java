package io.github.linianhui.util;

import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@AllArgsConstructor(staticName = "of")
public class ResourceUtil {

    public String readFileAsUtf8(
        final String expectedJsonFile
    ) throws IOException {
        return IOUtils.resourceToString(expectedJsonFile, StandardCharsets.UTF_8);
    }
}
