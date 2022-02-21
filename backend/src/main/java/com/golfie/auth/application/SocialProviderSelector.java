package com.golfie.auth.application;

import com.golfie.auth.exception.UnsupportedSocialProviderException;
import com.golfie.auth.infrastructure.OauthUserInfo;
import com.golfie.auth.infrastructure.SocialProvider;
import com.golfie.common.exception.ErrorCode;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Profile({"local", "dev"})
@Component
public class SocialProviderSelector implements ProviderSelectorFactory {

    @Override
    public OauthUserInfo getUserInfoFromSocialProvider(String code, String providerName) {
        SocialProvider socialProvider = getSocialProviderByName(providerName);
        return socialProvider.getUserInfo(code);
    }

    private SocialProvider getSocialProviderByName(String providerName) {
        return Arrays.stream(SocialProvider.values())
                .filter(provider -> provider.getProviderName().equals(providerName))
                .findFirst()
                .orElseThrow(() -> new UnsupportedSocialProviderException(ErrorCode.UNSUPPORTED_SOCIAL_PROVIDER));
    }

}
