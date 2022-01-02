package com.golfie.common.fixture;

import com.golfie.auth.infrastructure.SocialProvider;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class TestProviderDependencyInjector {

    private final TestLoginStrategy testLoginStrategy;

    public TestProviderDependencyInjector(TestLoginStrategy testLoginStrategy) {
        this.testLoginStrategy = testLoginStrategy;
    }

    @PostConstruct
    public void inject() {
        SocialProvider.TEST.setSocialLoginStrategy(testLoginStrategy);
    }
}
