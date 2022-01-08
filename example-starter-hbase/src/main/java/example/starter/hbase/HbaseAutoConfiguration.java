package example.starter.hbase;

import java.io.IOException;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(HbaseProperties.class)
@ConditionalOnClass(Connection.class)
public class HbaseAutoConfiguration {

    private static final String HBASE_QUORUM = "hbase.zookeeper.quorum";

    @Autowired
    private HbaseProperties hbaseProperties;

    @Bean
    @ConditionalOnMissingBean(HbaseTemplate.class)
    public HbaseTemplate hbaseTemplate() throws IOException {
        final org.apache.hadoop.conf.Configuration configuration = HBaseConfiguration.create();
        configuration.set(HBASE_QUORUM, this.hbaseProperties.getQuorum());
        return new HbaseTemplate(configuration);
    }
}
