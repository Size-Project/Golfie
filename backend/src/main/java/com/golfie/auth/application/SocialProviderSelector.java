package com.golfie.auth.application;

import com.golfie.auth.infrastructure.SocialProvider;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SocialProviderSelector {

    public SocialProvider getSocialProvider(String providerName) {
        return Arrays.stream(SocialProvider.values())
                .filter(provider -> provider.getProviderName().equals(providerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(""));
    }

}
