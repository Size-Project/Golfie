package com.golfie.auth.infrastructure.naver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NaverOauthInfo {
    public static String clientId;
    public static String clientSecret;
    public static String redirectUri;
    public static String userInfoRequestUri;
    public static String accessTokenRequestUri;

    @Value("${oauth.naver.client-id}")
    public void setClientId(String clientId) {
        NaverOauthInfo.clientId = clientId;
    }

    @Value("${oauth.naver.client-secret}")
    public void setClientSecret(String clientSecret) {
        NaverOauthInfo.clientSecret = clientSecret;
    }

    @Value("${oauth.naver.redirect-uri}")
    public void setRedirectUri(String redirectUri) {
        NaverOauthInfo.redirectUri = redirectUri;
    }

    @Value("${oauth.naver.user-info-request-uri}")
    public void setUserInfoRequestUri(String userInfoRequestUri) {
        NaverOauthInfo.userInfoRequestUri = userInfoRequestUri;
    }

    @Value("${oauth.naver.access-token-request-uri}")
    public void setAccessTokenRequestUri(String accessTokenRequestUri) {
        NaverOauthInfo.accessTokenRequestUri = accessTokenRequestUri;
    }
}
