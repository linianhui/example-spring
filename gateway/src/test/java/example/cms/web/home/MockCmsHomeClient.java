package example.cms.web.home;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.common.collect.ImmutableMap;
import example.cms.CmsHomeClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class MockCmsHomeClient {

    @Bean
    @Primary
    public CmsHomeClient cmsHomeClient() {
        CmsHomeClient client = mock(CmsHomeClient.class);

        when(client.getHome())
            .thenReturn(ImmutableMap.of("a", "form mock cms"));

        return client;
    }

}
