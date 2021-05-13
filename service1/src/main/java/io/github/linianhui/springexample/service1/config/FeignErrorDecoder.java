package io.github.linianhui.springexample.service1.config;

import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import io.github.linianhui.springexample.service1.error.FeignUpstreamException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Collection;

@Slf4j
@Primary
@Configuration
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(
        final String methodKey,
        final Response response
    ) {
        return FeignUpstreamException.builder()
            .methodKey(methodKey)
            .response(response)
            .responseContentType(readResponseContentType(response))
            .responseBody(readResponseBody(response))
            .build();
    }

    private MediaType readResponseContentType(
        final Response response
    ) {
        Collection<String> stringCollection = response.headers().get("Content-Type");
        if (CollectionUtils.isEmpty(stringCollection)) {
            return MediaType.APPLICATION_OCTET_STREAM;
        }
        return MediaType.valueOf(stringCollection.iterator().next());
    }

    private byte[] readResponseBody(
        final Response response
    ) {
        try {
            return Util.toByteArray(response.body().asInputStream());
        } catch (IOException e) {
            log.error("FeignErrorDecoder:readResponseBody:Exception", e);
            return new byte[0];
        }
    }
}
