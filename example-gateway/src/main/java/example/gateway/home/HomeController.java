package example.gateway.home;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import example.cms.CmsHomeClient;
import example.util.HttpServletRequestUtil;
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
    private CmsHomeClient homeClient;

    @Autowired
    private HttpServletRequest request;

    public static Object getHome(
        final HttpServletRequest request,
        final ExampleProperties exampleProperties
    ) {
        final Map<String, Object> map = new LinkedHashMap<>();
        map.put("request", HttpServletRequestUtil.getRequest((request)));
        map.put("example_properties", exampleProperties);
        return map;
    }

    @GetMapping
    public Object getHome(final HttpServletRequest request) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("gateway", getHome(request, exampleProperties));
        map.put("cms", homeClient.getHome());
        return map;
    }
}
