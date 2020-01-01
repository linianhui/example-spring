package io.github.linianhui.springexample.service2.home;

import io.github.linianhui.springexample.service2.home.ExampleProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ExampleProperties exampleProperties;

    @GetMapping
    public Object getHome() {
        return exampleProperties;
    }

}
