package io.github.linianhui.springexample.service1.home;

import io.github.linianhui.springexample.HttpTest;
import org.junit.jupiter.api.Test;

import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;


class HomeControllerTest extends HttpTest {

    @Test
    void test_get_home_should_return_ok() {
        http.get()
            .uri("/")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody()
            .consumeWith(getAssertUtil().assertJsonFileEquals(
                "/home/response.json",
                new String[]{"service1.request.headers"}
            ))
            .consumeWith(document("get-home"));
    }
}
