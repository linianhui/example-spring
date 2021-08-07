package example.cms;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(
    name = "cms",
    contextId = "file",
    primary = false,
    url = Constants.URL,
    configuration = CmsFileClient.Configuration.class
)
public interface CmsFileClient {

    @PostMapping(path = "/v1/file/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Object uploadFile(
        @RequestParam(name = "id") String id,
        @RequestPart(name = "files") MultipartFile multipartFiles
    );

    /**
     * @see <a href="https://github.com/OpenFeign/feign-form">https://github.com/OpenFeign/feign-form</a>
     */
    class Configuration {

        @Autowired
        private ObjectFactory<HttpMessageConverters> messageConverters;

        @Bean
        public Encoder feignEncoder() {
            return new SpringFormEncoder(new SpringEncoder(messageConverters));
        }
    }
}
