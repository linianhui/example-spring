package example.gateway.error;

import feign.Response;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.MediaType;

@Getter
@Builder
public class FeignUpstreamException extends RuntimeException {

    private String methodKey;

    private Response response;

    private MediaType responseContentType;

    private byte[] responseBody;
}
