package example.gateway.file;

import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

import example.HttpTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;


public class FileControllerTest extends HttpTest {

    @Test
    void test_upload_should_return_ok() throws Exception {
        MultipartBodyBuilder formDataBuilder = new MultipartBodyBuilder();
        formDataBuilder.part("id", "id-value");
        formDataBuilder.part("files", "this is a file").filename("file.name");
//        formDataBuilder.part(
//            "files",
//            new ClassPathResource("/v1/file/upload/ok/request-upload-file.xml", MultipartHttpMessageReader.class)
//        );
//        formDataBuilder.part(
//            "pojo",
//            getResourceUtil().readFileAsUtf8("/v1/file/upload/ok/request-pojo.json"), MediaType.APPLICATION_JSON
//        );

        Object formData = formDataBuilder.build();

        http.post()
            .uri("/v1/file/upload")
            .bodyValue(formData)
            .exchange()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectStatus().isOk()
            .expectBody()
            .consumeWith(getAssertUtil().assertJsonFileEquals("/v1/file/upload/ok/response.json", new String[]{"now"}))
            .consumeWith(document("v1-file-upload"));
    }
}
