package io.github.linianhui.springexample.service2.file;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/v1/file")
public class FileController {

    @PostMapping(path = "upload")
    public Object uploadFile(
        final @RequestParam(name = "id") String id,
        final @RequestPart(name = "pojo", required = false) POJO pojo,
        final @RequestPart(name = "files") MultipartFile[] multipartFiles
    ) {
        final Map<String, Object> map = new LinkedHashMap<>();
        map.put("now", LocalDateTime.now(ZoneOffset.UTC).toString());
        map.put("id", id);
        map.put("pojo", pojo);
        map.put("file_properties", Arrays.stream(multipartFiles).map(FileController::readFileProperty).toArray());
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
