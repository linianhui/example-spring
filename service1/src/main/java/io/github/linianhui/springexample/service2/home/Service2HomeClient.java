package io.github.linianhui.springexample.service2.home;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
    name = "service2",
    primary = false,
    url = "http://service2.spring-example",
    fallbackFactory = Service2HomeClientFallbackFactory.class
)
public interface Service2HomeClient {

    @GetMapping(path = "/")
    Object getHome();
}
