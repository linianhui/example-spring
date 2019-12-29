package io.github.linianhui.springexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Service2Application {

  public static void main(String[] args) {
    SpringApplication.run(Service2Application.class, args);
  }

}
