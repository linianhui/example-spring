package example.starter.hbase;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.data.hbase")
public class HbaseProperties {
    private String quorum;
}
