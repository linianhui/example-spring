package io.github.linianhui.springexample.service1;

import io.github.linianhui.springexample.service2.Service2HomeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class HomeController {

  @Autowired
  private ExampleProperties exampleProperties;

  @Autowired
  private Service2HomeClient service2HomeClient;

  @GetMapping
  public Object getHome() {
    Map<String, Object> map = new LinkedHashMap<>();
    map.put("service1", exampleProperties);
    map.put("service2", service2HomeClient.getHome());
    return map;
  }

}
