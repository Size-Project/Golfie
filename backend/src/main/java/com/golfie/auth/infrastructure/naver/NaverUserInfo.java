package com.golfie.auth.infrastructure.naver;

import com.golfie.auth.infrastructure.Oauth2UserInfo;

public class NaverUserInfo implements Oauth2UserInfo {
    @Override
    public String getProviderId() {
        return null;
    }

    @Override
    public String getEmail() {
        return null;
    }
}
