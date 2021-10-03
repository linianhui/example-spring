package example.cms.web.blog;

import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

import example.HttpTest;
import example.cms.dto.BlogDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;


public class BlogControllerTest extends HttpTest {

    @Test
    void test_create_blog_return_200_ok() throws Exception {
        BlogDto body = new BlogDto();
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

    @Test
    void test_get_blog_return_200_ok() throws Exception {
        test_create_blog_return_200_ok();

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

    @Test
    void test_get_blog_list_return_200_ok() throws Exception {
        test_create_blog_return_200_ok();
        test_create_blog_return_200_ok();

        http.get()
            .uri("/v1/blog")
            .exchange()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectStatus()
            .isOk()
            .expectBody()
            .jsonPath("$.length()").isEqualTo(2)
            .jsonPath("$[0].id").isEqualTo(1)
            .jsonPath("$[0].title").isEqualTo("test-title")
            .jsonPath("$[1].id").isEqualTo(2)
            .jsonPath("$[1].title").isEqualTo("test-title")
            .consumeWith(document("v1-blog-get-list"));
    }
}
