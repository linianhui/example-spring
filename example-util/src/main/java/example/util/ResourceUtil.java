package example.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;

@AllArgsConstructor(staticName = "of")
public class ResourceUtil {

    public String readFileAsUtf8(
        final String expectedJsonFile
    ) throws IOException {
        return IOUtils.resourceToString(expectedJsonFile, StandardCharsets.UTF_8);
    }
}
