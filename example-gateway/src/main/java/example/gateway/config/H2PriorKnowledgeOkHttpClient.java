package example.gateway.config;

import com.google.common.collect.Lists;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import org.springframework.stereotype.Component;

@Component
public class H2PriorKnowledgeOkHttpClient {

    private final OkHttpClient okHttpClient;

    public H2PriorKnowledgeOkHttpClient(final OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient.newBuilder()
            .protocols(Lists.newArrayList(Protocol.H2_PRIOR_KNOWLEDGE))
            .build();
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }
}
