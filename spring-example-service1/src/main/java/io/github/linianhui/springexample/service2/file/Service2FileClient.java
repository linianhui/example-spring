package io.github.linianhui.springexample.service2.file;

import io.github.linianhui.springexample.service2.FeignService2WithMultipartFile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignService2WithMultipartFile
public interface Service2FileClient {

    @PostMapping(path = "/v1/file/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Object uploadFile(
        @RequestParam(name = "id") String id,
        @RequestPart(name = "files") MultipartFile multipartFiles
    );
}
