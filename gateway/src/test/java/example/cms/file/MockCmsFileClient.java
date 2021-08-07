package example.cms.file;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import example.cms.CmsFileClient;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.web.multipart.MultipartFile;

@Profile("test")
@Configuration
public class MockCmsFileClient {

    private static Object buildUploadFileResult(
        final InvocationOnMock invocation
    ) {
        final Map<String, Object> map = new LinkedHashMap<>();
        map.put("now", LocalDateTime.now(ZoneOffset.UTC).toString());
        map.put("id", invocation.getArgument(0));
        //map.put("pojo", invocation.getArgument(1));
        List<Object> fileProperties = getMultipartFileList(invocation.getArgument(1))
            .stream()
            .map(MockCmsFileClient::readFileProperty)
            .collect(Collectors.toList());
        map.put("file_properties", fileProperties);
        return map;
    }

    private static List<MultipartFile> getMultipartFileList(Object argument) {
        if (argument instanceof List) {
            return (List<MultipartFile>) argument;
        }
        return Arrays.asList((MultipartFile) argument);
    }

    private static Object readFileProperty(
        final MultipartFile multipartFile
    ) {
        final Map<String, Object> filePropertyMap = new LinkedHashMap<>();
        filePropertyMap.put("name", multipartFile.getName());
        filePropertyMap.put("file_name", multipartFile.getOriginalFilename());
        filePropertyMap.put("length", multipartFile.getSize());
        filePropertyMap.put("content_type", multipartFile.getContentType());
        return filePropertyMap;
    }

    @Bean
    @Primary
    public CmsFileClient cmsFileClient() {
        CmsFileClient client = mock(CmsFileClient.class);

        when(client.uploadFile(anyString(), any()))
            .thenAnswer(MockCmsFileClient::buildUploadFileResult);

        return client;
    }
}
