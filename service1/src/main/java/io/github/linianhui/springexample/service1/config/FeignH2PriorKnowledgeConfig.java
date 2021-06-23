package io.github.linianhui.springexample.service1.config;

import feign.Client;
import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FeignH2PriorKnowledgeConfig {

    @Bean
    public Client feignH2PriorKnowledgeClient(
        final H2PriorKnowledgeOkHttpClient h2PriorKnowledgeOkHttpClient
    ) {
        return new OkHttpClient(h2PriorKnowledgeOkHttpClient.getOkHttpClient());
    }
}
