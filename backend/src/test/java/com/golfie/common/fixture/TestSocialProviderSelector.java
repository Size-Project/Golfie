package com.golfie.common.fixture;

import com.golfie.auth.application.ProviderSelectorFactory;
import com.golfie.auth.infrastructure.OauthUserInfo;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("test")
@Component
public class TestSocialProviderSelector implements ProviderSelectorFactory {

    @Override
    public OauthUserInfo getUserInfoFromSocialProvider(String code, String providerName) {
        return TestUserInfo.create();
    }

}
