package example.cms.web.file;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import example.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/v1/file")
public class FileController {
    @Autowired
    private HttpServletRequest request;

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

    @PostMapping(path = "upload")
    public Object uploadFile(
        final @RequestParam(name = "id") String id,
        final @RequestPart(name = "pojo", required = false) POJO pojo,
        final @RequestPart(name = "files") MultipartFile[] multipartFiles
    ) {
        final Map<String, Object> map = new LinkedHashMap<>();
        map.put("request", HttpServletRequestUtil.getRequest((request)));
        map.put("now", LocalDateTime.now(ZoneOffset.UTC).toString());
        map.put("id", id);
        map.put("pojo", pojo);
        map.put("file_properties", Arrays.stream(multipartFiles).map(FileController::readFileProperty).toArray());
        return map;
    }
}
