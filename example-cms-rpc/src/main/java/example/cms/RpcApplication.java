package example.cms;

import example.util.DescSortedBufferingApplicationStartup;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@EnableDubbo
@SpringBootApplication
@ConfigurationPropertiesScan
@MapperScan(basePackages = "example.cms.dao.mysql.mapper")
public class RpcApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(RpcApplication.class);
        app.setApplicationStartup(new DescSortedBufferingApplicationStartup(Integer.MAX_VALUE));
        app.run(args);
    }
}
