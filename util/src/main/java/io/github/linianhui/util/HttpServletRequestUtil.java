package io.github.linianhui.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

public class HttpServletRequestUtil {
    public static Map<String, Object> getRequest(
        final HttpServletRequest request
    ) {
        final Map<String, Object> map = new LinkedHashMap<>();
        map.put("class", request.getClass().getName());
        map.put("url", request.getRequestURI());
        map.put("protocol", request.getProtocol());
        map.put("headers", getRequestHeaders(request));
        return map;
    }

    public static Map<String, String> getRequestHeaders(
        final HttpServletRequest request
    ) {
        final Map<String, String> map = new LinkedHashMap<>();
        final Enumeration<String> names = request.getHeaderNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            map.put(name, request.getHeader(name));
        }
        return map;
    }
}
