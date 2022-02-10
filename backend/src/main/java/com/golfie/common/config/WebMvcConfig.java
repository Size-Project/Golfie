package com.golfie.common.config;

import com.golfie.auth.util.AuthArgumentResolver;
import com.golfie.auth.util.JwtTokenProvider;
import com.golfie.common.interceptor.LoginInterceptor;
import com.golfie.common.interceptor.PathMatcherInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public LoginInterceptor createLoginInterceptor() {
        return new LoginInterceptor(jwtTokenProvider);
    }

    @Bean
    public AuthArgumentResolver createAuthArgumentResolver() {
        return new AuthArgumentResolver(jwtTokenProvider);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(createAuthArgumentResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        PathMatcherInterceptor pathMatcherInterceptor = new PathMatcherInterceptor(createLoginInterceptor())
                .addPathPatterns("/api/feeds/**", HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE)
                .addPathPatterns("/api/feeds/me", HttpMethod.GET)
                .addPathPatterns("/api/users/**", HttpMethod.POST, HttpMethod.DELETE)
                .addPathPatterns("/api/users/me", HttpMethod.GET)
                .excludePathPatterns("/api/feeds", HttpMethod.GET)
                .excludePathPatterns("/api/login/*", HttpMethod.POST)
                .excludePathPatterns("/api/signup/*", HttpMethod.POST)
                .excludePathPatterns("/api/validate/*", HttpMethod.POST);

        registry.addInterceptor(pathMatcherInterceptor)
                .addPathPatterns("/**");
    }
}
