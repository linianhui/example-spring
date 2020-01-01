package io.github.linianhui.springexample.service2.file;

import io.github.linianhui.springexample.service2.FeignService2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@FeignService2
public interface Service2FileClient {

    @PostMapping(path = "/v1/file/upload")
    Object uploadFile(
        @RequestPart(name = "id") String id,
        @RequestPart(name = "pojo") Map pojo,
        @RequestPart(name = "files") MultipartFile[] multipartFiles
    );
}
