package example.gateway.remote.cms;

import example.gateway.remote.cms.config.FeignH2PriorKnowledgeConfig;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
    name = "cms",
    contextId = "home",
    primary = false,
    url = Constants.URL,
    fallbackFactory = CmsHttpHomeClient.DefaultFallbackFactory.class,
    configuration = FeignH2PriorKnowledgeConfig.class
)
public interface CmsHttpHomeClient {

    @GetMapping(path = "/")
    Object getHome();

    /**
     * FeignCircuitBreakerInvocationHandler
     **/
    @Component
    class DefaultFallbackFactory implements FallbackFactory<CmsHttpHomeClient> {

        @Override
        public CmsHttpHomeClient create(Throwable cause) {
            // log error;
            cause.printStackTrace(System.out);
            return new CmsHttpHomeClient() {
                @Override
                public Object getHome() {
                    return "from fallback";
                }
            };
        }
    }
}
