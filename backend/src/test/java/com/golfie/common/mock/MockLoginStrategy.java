package com.golfie.common.mock;

import com.golfie.auth.infrastructure.OauthUserInfo;
import com.golfie.auth.infrastructure.SocialLoginStrategy;
import com.golfie.common.fixture.TestUserInfo;
import org.springframework.stereotype.Component;

@Component
public class MockLoginStrategy implements SocialLoginStrategy {

    @Override
    public OauthUserInfo getUserInfo(String code) {
        return TestUserInfo.create();
    }

    @Override
    public String getAccessToken(String code) {
        return null;
    }

}
