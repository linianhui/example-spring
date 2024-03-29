package example.cms.web.home;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

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
    private HttpServletRequest request;

    @GetMapping
    public Object getHome() {
        final Map<String, Object> map = new LinkedHashMap<>();
        map.put("request", HttpServletRequestUtil.getRequest(request));
        map.put("example_properties", exampleProperties);
        return map;
    }
}
