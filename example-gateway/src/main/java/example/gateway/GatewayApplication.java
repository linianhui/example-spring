package example.gateway;

import example.util.DescSortedBufferingApplicationStartup;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDubbo
@SpringBootApplication
@ConfigurationPropertiesScan
@EnableFeignClients
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(GatewayApplication.class);
        app.setApplicationStartup(new DescSortedBufferingApplicationStartup(Integer.MAX_VALUE));
        app.run(args);
    }
}
