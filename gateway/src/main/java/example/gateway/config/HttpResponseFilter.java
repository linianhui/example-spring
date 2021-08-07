package example.gateway.config;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.TraceContext;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;


@Component
public class HttpResponseFilter extends GenericFilterBean {

    private final Tracer tracer;

    HttpResponseFilter(final Tracer tracer) {
        this.tracer = tracer;
    }

    public static void addXB3TraceIdToHttpResponseHeader(
        final ServletResponse response,
        final Tracer tracer
    ) {
        if (response==null) {
            return;
        }

        if (tracer==null) {
            return;
        }

        Span currentSpan = tracer.currentSpan();
        if (currentSpan==null) {
            return;
        }

        TraceContext traceContext = currentSpan.context();
        if (traceContext==null) {
            return;
        }

        if (response instanceof HttpServletResponse) {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            String traceId = traceContext.traceId();
            if (StringUtils.isNotBlank(traceId)) {
                httpServletResponse.addHeader("X-B3-TraceId", traceId);
            }
        }
    }

    /**
     * 添加trace-id.
     */
    @Override
    public void doFilter(
        ServletRequest request,
        ServletResponse response,
        FilterChain chain
    ) throws IOException, ServletException {
        addXB3TraceIdToHttpResponseHeader(response, tracer);
        chain.doFilter(request, response);
    }
}