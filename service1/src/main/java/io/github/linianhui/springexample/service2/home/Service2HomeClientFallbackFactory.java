package io.github.linianhui.springexample.service2.home;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class Service2HomeClientFallbackFactory implements FallbackFactory<Service2HomeClient> {

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
