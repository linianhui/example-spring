package example.cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
@MapperScan(basePackages = "example.cms.dao.mysql.mapper")
public class WebApplication {

    public static void main(String[] args) {
        System.setProperty("zookeeper.client.sasl", "false");
        SpringApplication.run(WebApplication.class, args);
    }

}
