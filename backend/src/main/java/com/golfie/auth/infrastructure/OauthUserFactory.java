package com.golfie.auth.infrastructure;

public interface OauthUserFactory {
    Oauth2UserInfo getUserInfo(String code);
}
