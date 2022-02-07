package com.golfie.common.interceptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Slf4j
public class PathMatcherInterceptor implements HandlerInterceptor {

    private final HandlerInterceptor handlerInterceptor;
    private final PathMatcher pathMatcher;
    private final Map<String, List<HttpMethod>> excludeRegistry;
    private final Map<String, List<HttpMethod>> includeRegistry;

    public PathMatcherInterceptor(HandlerInterceptor handlerInterceptor) {
        this.handlerInterceptor = handlerInterceptor;
        this.pathMatcher = new AntPathMatcher();
        this.includeRegistry = new HashMap<>();
        this.excludeRegistry = new HashMap<>();
    }

    public PathMatcherInterceptor addPathPatterns(String pathPattern, HttpMethod... httpMethods) {
        includeRegistry.put(pathPattern, List.of(httpMethods));
        return this;
    }

    public PathMatcherInterceptor excludePathPatterns(String pathPattern, HttpMethod... httpMethods) {
        excludeRegistry.put(pathPattern, List.of(httpMethods));
        return this;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("PathMatcherInterceptor.preHandle");
        String requestUrl = request.getRequestURI();
        String requestMethod = request.getMethod();

        for (Entry<String, List<HttpMethod>> entry : excludeRegistry.entrySet()) {
            boolean isUrlMatch = pathMatcher.match(entry.getKey(), requestUrl);
            boolean isMethodMatch = entry.getValue().contains(HttpMethod.resolve(requestMethod));
            if (isUrlMatch && isMethodMatch) {
                return true;
            }
        }

        for (Entry<String, List<HttpMethod>> entry : includeRegistry.entrySet()) {
            boolean isUrlMatch = pathMatcher.match(entry.getKey(), requestUrl);
            boolean isMethodMatch = entry.getValue().contains(HttpMethod.resolve(requestMethod));
            if (isUrlMatch && isMethodMatch) {
                return handlerInterceptor.preHandle(request, response, handler);
            }
        }

        return true;
    }
}
