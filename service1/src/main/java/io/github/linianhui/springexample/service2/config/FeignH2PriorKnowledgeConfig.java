package io.github.linianhui.springexample.service2.config;

import feign.Client;
import feign.okhttp.OkHttpClient;
import io.github.linianhui.springexample.service1.config.H2PriorKnowledgeOkHttpClient;
import org.springframework.context.annotation.Bean;


public class FeignH2PriorKnowledgeConfig {

    @Bean
    public Client feignH2PriorKnowledgeClient(
        final H2PriorKnowledgeOkHttpClient h2PriorKnowledgeOkHttpClient
    ) {
        return new OkHttpClient(h2PriorKnowledgeOkHttpClient.getOkHttpClient());
    }
}
