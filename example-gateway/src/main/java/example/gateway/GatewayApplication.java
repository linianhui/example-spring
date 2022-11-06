package example.gateway;

import example.util.DescSortedBufferingApplicationStartup;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;

@EnableDubbo
@SpringBootApplication
@ConfigurationPropertiesScan
@EnableFeignClients
@RestController
@RequestMapping(path = ".app")
public class GatewayApplication {

    private static final DescSortedBufferingApplicationStartup STARTUP = new DescSortedBufferingApplicationStartup(Integer.MAX_VALUE);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(GatewayApplication.class);
        app.setApplicationStartup(STARTUP);
        app.run(args);
    }

    @GetMapping(path = "startup")
    public Object get() {
        return STARTUP.getCounts();
    }
}
