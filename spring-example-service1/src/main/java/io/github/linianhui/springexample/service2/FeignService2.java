package io.github.linianhui.springexample.service2;

import org.springframework.cloud.openfeign.FeignClient;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@FeignClient(
    name = "service2",
    primary = false,
    url = "http://service2.spring-example",
    configuration = FeignMultipartEncoderConfiguration.class
)
public @interface FeignService2 {

}
