package com.golfie.auth.infrastructure.kakao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KakaoOauthInfo {
    public static String clientId;
    public static String signupRedirectUri;
    public static String loginRedirectUri;
    public static String userInfoRequestUri;
    public static String accessTokenRequestUri;
    public static String redirectUri;

    @Value("${oauth.kakao.client-id}")
    public void setClientId(String clientId) {
        KakaoOauthInfo.clientId = clientId;
    }

    @Value("${oauth.kakao.signup-redirect-uri}")
    public void setSignupRedirectUri(String redirectUri) {
        KakaoOauthInfo.signupRedirectUri = redirectUri;
    }

    @Value("${oauth.kakao.login-redirect-uri}")
    public void setLoginRedirectUri(String redirectUri) {
        KakaoOauthInfo.loginRedirectUri = redirectUri;
    }

    @Value("${oauth.kakao.user-info-request-uri}")
    public void setUserInfoRequestUri(String userInfoRequestUri) {
        KakaoOauthInfo.userInfoRequestUri = userInfoRequestUri;
    }

    @Value("${oauth.kakao.access-token-request-uri}")
    public void setAccessTokenRequestUri(String accessTokenRequestUri) {
        KakaoOauthInfo.accessTokenRequestUri = accessTokenRequestUri;
    }

    public static void setRedirectUri(String uri) {
        redirectUri = uri;
    }
}
