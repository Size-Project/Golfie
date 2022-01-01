package com.golfie.auth.infrastructure.naver;

import com.golfie.auth.infrastructure.Oauth2UserInfo;
import com.golfie.auth.infrastructure.OauthUserFactory;
import org.springframework.stereotype.Component;

@Component
public class NaverUserFactory implements OauthUserFactory {
    @Override
    public Oauth2UserInfo getUserInfo(String code) {
        return null;
    }
}
