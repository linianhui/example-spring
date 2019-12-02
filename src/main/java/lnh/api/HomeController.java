package lnh.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class HomeController {

  @Autowired
  private HttpServletRequest request;

  @Autowired
  private ExampleProperties exampleProperties;

  @GetMapping
  public Object getHome() {
    Map map = new LinkedHashMap();
    map.put("text", "this is a java api");
    map.put("author", "lnhcode@outlook.com");
    map.put("example_property_url", request.getRequestURL().toString() + "example-property");
    return map;
  }

  @GetMapping("example-property")
  public Object getExampleProperties() {
    return exampleProperties;
  }

}
