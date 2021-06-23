package io.github.linianhui.springexample.service2;

import io.github.linianhui.springexample.service1.config.FeignH2PriorKnowledgeConfig;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
    name = "service2",
    primary = false,
    url = Constants.URL,
    fallbackFactory = Service2HomeClient.DefaultFallbackFactory.class,
    configuration = FeignH2PriorKnowledgeConfig.class
)
public interface Service2HomeClient {

    @GetMapping(path = "/")
    Object getHome();

    /**
     * FeignCircuitBreakerInvocationHandler
     * **/
    @Component
    class DefaultFallbackFactory implements FallbackFactory<Service2HomeClient> {

        @Override
        public Service2HomeClient create(Throwable cause) {
            // log error;
            cause.printStackTrace(System.out);
            return new Service2HomeClient() {
                @Override
                public Object getHome() {
                    return "from fallback";
                }
            };
        }
    }
}
