package com.golfie.common.config;

import com.golfie.auth.util.AuthArgumentResolver;
import com.golfie.auth.util.JwtTokenProvider;
import com.golfie.common.interceptor.LoginInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        return new LoginInterceptor();
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
        registry.addInterceptor(createLoginInterceptor())
                .addPathPatterns("/api/users/me")
                .excludePathPatterns("/api/prepareSignUp/oauth");
    }
}
