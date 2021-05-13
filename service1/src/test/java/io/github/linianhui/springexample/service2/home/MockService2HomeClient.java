package io.github.linianhui.springexample.service2.home;

import com.google.common.collect.ImmutableMap;
import io.github.linianhui.springexample.service2.home.Service2HomeClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Profile("test")
@Configuration
public class MockService2HomeClient {

    @Bean
    @Primary
    public Service2HomeClient service2HomeClient() {
        Service2HomeClient client = mock(Service2HomeClient.class);

        when(client.getHome())
            .thenReturn(ImmutableMap.of("a", "form mock service2"));

        return client;
    }

}
