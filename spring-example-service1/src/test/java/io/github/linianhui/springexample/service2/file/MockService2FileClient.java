package io.github.linianhui.springexample.service2.file;

import org.mockito.invocation.InvocationOnMock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Profile("test")
@Configuration
public class MockService2FileClient {

    @Bean
    @Primary
    public Service2FileClient service2FileClient() {
        Service2FileClient client = mock(Service2FileClient.class);

        when(client.uploadFile(anyString(), anyMap(), any()))
            .thenAnswer(MockService2FileClient::buildUploadFileResult);

        return client;
    }

    private static Object buildUploadFileResult(
        final InvocationOnMock invocation
    ) {
        final Map<String, Object> map = new LinkedHashMap<>();
        map.put("now", LocalDateTime.now(ZoneOffset.UTC).toString());
        map.put("id", invocation.getArgument(0));
        map.put("pojo", invocation.getArgument(1));
        Object[] fileProperties = Arrays.stream((MultipartFile[]) invocation.getArgument(2))
            .map(MockService2FileClient::readFileProperty)
            .toArray();
        map.put("file_properties", fileProperties);
        return map;
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
}
