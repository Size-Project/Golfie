package com.golfie.auth.application;

import com.golfie.auth.infrastructure.SocialProvider;

public interface ProviderSelectorFactory {
    SocialProvider getSocialProvider(String providerName);
}
