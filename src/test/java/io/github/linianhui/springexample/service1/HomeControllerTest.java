package io.github.linianhui.springexample.service1;

import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

import org.junit.jupiter.api.Test;


class HomeControllerTest extends HttpTest {

  @Test
  void test_get_home() {
    http.get()
        .uri("/")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .consumeWith(document("get-home"));
  }
}
