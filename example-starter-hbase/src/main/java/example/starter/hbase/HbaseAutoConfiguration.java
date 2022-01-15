package example.starter.hbase;

import java.io.IOException;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.client.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(HbaseProperties.class)
@ConditionalOnProperty(
    prefix = "spring.data.hbase",
    name = "enabled",
    havingValue = "true"
)
@ConditionalOnClass(Connection.class)
public class HbaseAutoConfiguration {

    @Autowired
    private HbaseProperties hbaseProperties;

    @Bean
    @ConditionalOnMissingBean(HbaseTemplate.class)
    public HbaseTemplate hbaseTemplate() throws IOException {
        final org.apache.hadoop.conf.Configuration configuration = HBaseConfiguration.create();
        configuration.set(HConstants.ZOOKEEPER_QUORUM, this.hbaseProperties.getQuorum());
        return new HbaseTemplate(configuration);
    }
}
