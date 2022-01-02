package com.golfie.common.fixture;

import com.golfie.auth.infrastructure.OauthUserInfo;
import com.golfie.auth.infrastructure.SocialLoginStrategy;
import org.springframework.stereotype.Component;

@Component
public class TestLoginStrategy implements SocialLoginStrategy {

    @Override
    public OauthUserInfo getUserInfo(String code) {
        return TestUserInfo.of();
    }

    @Override
    public String getAccessToken(String code) {
        return null;
    }

}
