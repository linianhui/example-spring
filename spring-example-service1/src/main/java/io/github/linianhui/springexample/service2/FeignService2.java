package io.github.linianhui.springexample.service2;

import org.springframework.cloud.openfeign.FeignClient;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@FeignClient(
    primary = false,
    name = "service2",
    url = "http://service2.spring-example"
)
public @interface FeignService2 {

}
