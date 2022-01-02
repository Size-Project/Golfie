package com.golfie.auth.application;

import com.golfie.auth.infrastructure.OauthUserInfo;

public interface ProviderSelectorFactory {
    OauthUserInfo getUserInfoFromSocialProvider(String code, String providerName);
}
