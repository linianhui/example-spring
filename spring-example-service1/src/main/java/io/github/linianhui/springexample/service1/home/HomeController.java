package io.github.linianhui.springexample.service1.home;

import io.github.linianhui.springexample.service2.home.Service2HomeClient;
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

    @Autowired
    private Service2HomeClient service2HomeClient;

    @GetMapping
    public Object getHome(final HttpServletRequest request) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("service1", getHome(request, exampleProperties));
        map.put("service2", service2HomeClient.getHome());
        return map;
    }

    public static Object getHome(
        final HttpServletRequest request,
        final ExampleProperties exampleProperties
    ) {
        final Map<String, Object> map = new LinkedHashMap<>();
        map.put("request", getRequest(request));
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
