package io.github.linianhui.springexample.service2.config;


import io.undertow.Undertow;
import io.undertow.UndertowOptions;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.embedded.undertow.UndertowBuilderCustomizer;
import org.springframework.boot.web.embedded.undertow.UndertowDeploymentInfoCustomizer;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
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
     * 覆盖默认的UndertowServletWebServerFactory
     * org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryConfiguration.EmbeddedUndertow
     */
    @Bean
    public UndertowServletWebServerFactory undertowServletWebServerFactory(
        final ObjectProvider<UndertowDeploymentInfoCustomizer> deploymentInfoCustomizers,
        final ObjectProvider<UndertowBuilderCustomizer> builderCustomizers
    ) {
        final UndertowServletWebServerFactory factory = new UndertowServletWebServerFactory();

        deploymentInfoCustomizers
            .orderedStream()
            .forEach(factory::addDeploymentInfoCustomizers);

        builderCustomizers
            .orderedStream()
            .forEach(factory::addBuilderCustomizers);

        factory.addBuilderCustomizers(Http2Config::undertowEnableHttp2);

        return factory;
    }

    private static Undertow.Builder undertowEnableHttp2(
        final Undertow.Builder builder
    ) {
        return builder.setServerOption(UndertowOptions.ENABLE_HTTP2, true);
    }
}
