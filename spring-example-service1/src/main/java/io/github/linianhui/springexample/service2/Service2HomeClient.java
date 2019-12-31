package io.github.linianhui.springexample.service2;

import org.springframework.web.bind.annotation.GetMapping;

@FeignService2
public interface Service2HomeClient {

    @GetMapping(path = "/")
    Object getHome();
}
