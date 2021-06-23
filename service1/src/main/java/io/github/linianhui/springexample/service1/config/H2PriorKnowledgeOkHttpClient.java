package io.github.linianhui.springexample.service1.config;


import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import org.springframework.stereotype.Component;

@Component
public class H2PriorKnowledgeOkHttpClient {

    private final OkHttpClient okHttpClient;

    public H2PriorKnowledgeOkHttpClient(final OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient.newBuilder()
            .protocols(List.of(Protocol.H2_PRIOR_KNOWLEDGE))
            .build();
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }
}
