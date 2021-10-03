package example.cms.config;

import example.gateway.config.H2PriorKnowledgeOkHttpClient;
import feign.Client;
import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;


public class FeignH2PriorKnowledgeConfig {

    @Bean
    public Client feignH2PriorKnowledgeClient(
        final H2PriorKnowledgeOkHttpClient h2PriorKnowledgeOkHttpClient
    ) {
        return new OkHttpClient(h2PriorKnowledgeOkHttpClient.getOkHttpClient());
    }
}
