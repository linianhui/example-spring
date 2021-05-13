package io.github.linianhui.springexample.service2.config;

import io.undertow.Undertow;
import io.undertow.UndertowOptions;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.embedded.undertow.UndertowBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(
    name = "server.http2.enabled",
    matchIfMissing = false,
    havingValue = "true"
)
public class Http2Config {

    /**
     * 启用HTTP2
     */
    @Bean
    public UndertowBuilderCustomizer undertowBuilderCustomizer() {
        return new UndertowBuilderCustomizer() {
            @Override
            public void customize(Undertow.Builder builder) {
                builder.setServerOption(UndertowOptions.ENABLE_HTTP2, true);
            }
        };
    }
}
