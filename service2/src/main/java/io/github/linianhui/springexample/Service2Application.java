package io.github.linianhui.springexample;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
@MapperScan(basePackages = "io.github.linianhui.springexample.service2.dao")
public class Service2Application {

    public static void main(String[] args) {
        SpringApplication.run(Service2Application.class, args);
    }

}
