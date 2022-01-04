package com.golfie.auth.infrastructure.naver;

import com.golfie.auth.infrastructure.OauthUserInfo;
import com.golfie.auth.infrastructure.SocialLoginStrategy;

public class NaverLoginStrategy implements SocialLoginStrategy {
    @Override
    public OauthUserInfo getUserInfo(String code) {
        return null;
    }

    @Override
    public String getAccessToken(String code) {
        return null;
    }
}
