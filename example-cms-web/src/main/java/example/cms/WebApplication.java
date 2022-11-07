package example.cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.core.metrics.jfr.FlightRecorderApplicationStartup;

@SpringBootApplication
@ConfigurationPropertiesScan
@MapperScan(basePackages = "example.cms.dao.mysql.mapper")
public class WebApplication {

    public static void main(String[] args) {
        System.setProperty("zookeeper.client.sasl", "false");
        SpringApplication app = new SpringApplication(WebApplication.class);
        app.setApplicationStartup(new FlightRecorderApplicationStartup());
        app.run(args);
    }

}
