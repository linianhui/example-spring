package io.github.linianhui.springexample.service2.home;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

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
    public Object getHome(final HttpServletRequest request) {
        final Map<String, Object> map = new LinkedHashMap<>();
        map.put("request", getRequest(request));
        map.put("request_class", request.getClass().getName());
        map.put("example_properties", exampleProperties);
        return map;
    }

    private static Map<String, Object> getRequest(
        final HttpServletRequest request
    ) {
        final Map<String, Object> map = new LinkedHashMap<>();
        map.put("protocol", request.getProtocol());
        return map;
    }
}
