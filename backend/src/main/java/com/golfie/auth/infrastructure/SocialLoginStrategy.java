package com.golfie.auth.infrastructure;

public interface SocialLoginStrategy {
    OauthUserInfo getUserInfo(String code);
    String getAccessToken(String code);
}
