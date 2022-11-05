package example.cms;

import example.util.DescSortedBufferingApplicationStartup;
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
        SpringApplication app = new SpringApplication(WebApplication.class);
        app.setApplicationStartup(new DescSortedBufferingApplicationStartup(Integer.MAX_VALUE));
        app.run(args);
    }

}
