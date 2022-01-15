package example.cms.web.blog;

import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

import example.HttpTest;
import example.cms.api.dto.BlogSaveDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;


public class BlogControllerTest extends HttpTest {

    //@Test
    void test_save_return_200_ok() throws Exception {
        BlogSaveDto body = new BlogSaveDto();
        body.setUserId("123");
        body.setTitle("test-title");
        http.post()
            .uri("/v1/blog")
            .bodyValue(body)
            .exchange()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectStatus()
            .isOk()
            .expectBody()
            .json("1")
            .consumeWith(document("v1-blog-create"));
    }

    //@Test
    void test_get_by_id_return_200_ok() throws Exception {
        test_save_return_200_ok();

        http.get()
            .uri("/v1/blog/{blogId}", 1)
            .exchange()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectStatus()
            .isOk()
            .expectBody()
            .jsonPath("$.id").isEqualTo(1)
            .jsonPath("$.title").isEqualTo("test-title")
            .consumeWith(document("v1-blog-get-by-id"));
    }

    //@Test
    void test_get_by_user_id_return_200_ok() throws Exception {
        test_save_return_200_ok();
        test_save_return_200_ok();

        http.get()
            .uri("/v1/blog?userId=123")
            .exchange()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectStatus()
            .isOk()
            .expectBody()
            .jsonPath("$[0].id").isEqualTo(1)
            .jsonPath("$[0].title").isEqualTo("test-title")
            .jsonPath("$[1].id").isEqualTo(2)
            .jsonPath("$[1].title").isEqualTo("test-title")
            .consumeWith(document("v1-blog-get-list"));
    }
}
