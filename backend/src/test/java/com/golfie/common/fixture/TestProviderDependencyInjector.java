package com.golfie.common.fixture;

import com.golfie.auth.infrastructure.SocialProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

@Profile("test")
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
