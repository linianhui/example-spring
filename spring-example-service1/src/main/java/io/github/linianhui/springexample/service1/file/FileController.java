package io.github.linianhui.springexample.service1.file;

import lombok.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/v1/file")
public class FileController {

    @PostMapping("upload")
    public Object uploadFile(
        final @RequestPart(name = "id") String id,
        final @RequestPart(name = "pojo") POJO pojo,
        final @RequestPart(name = "files") MultipartFile[] multipartFiles
    ) {
        final Map<String, Object> map = new LinkedHashMap<>();
        map.put("now", LocalDateTime.now(ZoneOffset.UTC).toString());
        map.put("id", id);
        map.put("pojo", pojo);
        map.put("file_properties", Arrays.stream(multipartFiles).map(this::readFileProperty).toArray());
        return map;
    }

    private Object readFileProperty(
        final MultipartFile multipartFile
    ) {
        final Map<String, Object> filePropertyMap = new LinkedHashMap<>();
        filePropertyMap.put("name", multipartFile.getName());
        filePropertyMap.put("file_name", multipartFile.getOriginalFilename());
        filePropertyMap.put("length", multipartFile.getSize());
        filePropertyMap.put("content_type", multipartFile.getContentType());
        return filePropertyMap;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class POJO {
        private String id;
        private String name;
    }
}
