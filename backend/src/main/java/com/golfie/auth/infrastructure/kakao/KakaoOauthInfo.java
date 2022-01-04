package com.golfie.auth.infrastructure.kakao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KakaoOauthInfo {
    public static String clientId;
    public static String redirectUri;
    public static String userInfoRequestUri;
    public static String accessTokenRequestUri;

    @Value("${oauth.kakao.client-id}")
    public void setClientId(String clientId) {
        KakaoOauthInfo.clientId = clientId;
    }

    @Value("${oauth.kakao.redirect-uri}")
    public void setRedirectUri(String redirectUri) {
        KakaoOauthInfo.redirectUri = redirectUri;
    }

    @Value("${oauth.kakao.user-info-request-uri}")
    public void setUserInfoRequestUri(String userInfoRequestUri) {
        KakaoOauthInfo.userInfoRequestUri = userInfoRequestUri;
    }

    @Value("${oauth.kakao.access-token-request-uri}")
    public void setAccessTokenRequestUri(String accessTokenRequestUri) {
        KakaoOauthInfo.accessTokenRequestUri = accessTokenRequestUri;
    }
}
