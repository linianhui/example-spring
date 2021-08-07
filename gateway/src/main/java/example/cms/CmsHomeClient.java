package example.cms;

import example.cms.config.FeignH2PriorKnowledgeConfig;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
    name = "cms",
    contextId = "home",
    primary = false,
    url = Constants.URL,
    fallbackFactory = CmsHomeClient.DefaultFallbackFactory.class,
    configuration = FeignH2PriorKnowledgeConfig.class
)
public interface CmsHomeClient {

    @GetMapping(path = "/")
    Object getHome();

    /**
     * FeignCircuitBreakerInvocationHandler
     **/
    @Component
    class DefaultFallbackFactory implements FallbackFactory<CmsHomeClient> {

        @Override
        public CmsHomeClient create(Throwable cause) {
            // log error;
            cause.printStackTrace(System.out);
            return new CmsHomeClient() {
                @Override
                public Object getHome() {
                    return "from fallback";
                }
            };
        }
    }
}
