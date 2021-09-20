package example.cms.web.error;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;

import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Slf4j
@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    private ResponseEntity<Map> exception(
        final Exception exception,
        final HttpServletRequest request
    ) {
        log.error("Exception", exception);

        Map<String, Object> errorResponse = new LinkedHashMap<>();
        errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.put("method", request.getMethod());
        errorResponse.put("path", getFullRequestUrl(request));
        errorResponse.put("exception", buildExceptionResponse(exception));

        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(errorResponse);
    }

    private Map<String, Object> buildExceptionResponse(final Exception exception) {
        Map<String, Object> exceptionResponse = new LinkedHashMap<>();
        exceptionResponse.put("message", exception.getMessage());
        exceptionResponse.put(
            "stack",
            Arrays.stream(exception.getStackTrace())
                .map(StackTraceElement::toString)
                .collect(Collectors.toList())
        );
        return exceptionResponse;
    }

    private String getFullRequestUrl(final HttpServletRequest request) {
        String fullRequestUrl = request.getRequestURI();
        final String queryString = request.getQueryString();
        if (StringUtils.isNotBlank(queryString)) {
            fullRequestUrl = fullRequestUrl + "?" + queryString;
        }
        return fullRequestUrl;
    }
}
