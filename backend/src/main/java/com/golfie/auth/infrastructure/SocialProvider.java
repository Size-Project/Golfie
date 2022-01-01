package com.golfie.auth.infrastructure;

import com.golfie.auth.infrastructure.kakao.KakaoLoginStrategy;
import com.golfie.auth.infrastructure.kakao.KakaoOauthInfo;
import com.golfie.auth.infrastructure.naver.NaverLoginStrategy;

public enum SocialProvider {
    KAKAO("Kakao", new KakaoLoginStrategy(
            KakaoOauthInfo.clientId,
            KakaoOauthInfo.redirectUri,
            KakaoOauthInfo.userInfoRequestUri,
            KakaoOauthInfo.accessTokenRequestUri)
    ),
    NAVER("Naver", new NaverLoginStrategy()),
    TEST("Test", null);

    private final String providerName;
    private final SocialLoginStrategy socialLoginStrategy;

    SocialProvider(String providerName, SocialLoginStrategy socialLoginStrategy) {
        this.providerName = providerName;
        this.socialLoginStrategy = socialLoginStrategy;
    }

    public OauthUserInfo getUserInfo(String code) {
        return socialLoginStrategy.getUserInfo(code);
    }

    public String getProviderName() {
        return providerName;
    }
}
