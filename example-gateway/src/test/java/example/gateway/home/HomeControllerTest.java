package example.gateway.home;

import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

import example.HttpTest;
import org.junit.jupiter.api.Test;


class HomeControllerTest extends HttpTest {

    void test_get_home_should_return_ok() {
        http.get()
            .uri("/")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody()
            .consumeWith(getAssertUtil().assertJsonFileEquals(
                "/home/response.json",
                new String[]{"gateway.request.headers"}
            ))
            .consumeWith(document("get-home"));
    }
}
