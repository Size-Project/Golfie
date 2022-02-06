package com.golfie.common.mock;

import com.golfie.auth.application.ProviderSelectorFactory;
import com.golfie.auth.infrastructure.OauthUserInfo;
import com.golfie.common.fixture.TestUserInfo;
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
