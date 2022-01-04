package com.golfie.auth.infrastructure;

import com.golfie.auth.infrastructure.kakao.KakaoLoginStrategy;
import com.golfie.auth.infrastructure.kakao.KakaoOauthInfo;
import com.golfie.auth.infrastructure.naver.NaverLoginStrategy;
import org.springframework.context.annotation.Profile;

public enum SocialProvider {
    KAKAO("KAKAO", new KakaoLoginStrategy(
            KakaoOauthInfo.clientId,
            KakaoOauthInfo.redirectUri,
            KakaoOauthInfo.userInfoRequestUri,
            KakaoOauthInfo.accessTokenRequestUri)
    ),
    NAVER("NAVER", new NaverLoginStrategy()),
    TEST("TEST");

    private final String providerName;
    private SocialLoginStrategy socialLoginStrategy;

    SocialProvider(String providerName, SocialLoginStrategy socialLoginStrategy) {
        this.providerName = providerName;
        this.socialLoginStrategy = socialLoginStrategy;
    }

    SocialProvider(String providerName) {
        this.providerName = providerName;
    }

    public OauthUserInfo getUserInfo(String code) {
        return socialLoginStrategy.getUserInfo(code);
    }

    public String getProviderName() {
        return providerName;
    }

    public void setSocialLoginStrategy(SocialLoginStrategy socialLoginStrategy) {
        this.socialLoginStrategy = socialLoginStrategy;
    }
}
