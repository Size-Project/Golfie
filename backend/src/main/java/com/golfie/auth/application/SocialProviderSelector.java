package com.golfie.auth.application;

import com.golfie.auth.infrastructure.kakao.KakaoUserFactory;
import com.golfie.auth.infrastructure.naver.NaverUserFactory;
import com.golfie.auth.infrastructure.OauthUserFactory;
import com.golfie.auth.infrastructure.OauthUserInfo;
import org.springframework.stereotype.Component;

@Component
public class SocialClient {

    private final KakaoUserFactory kakaoUserFactory;
    private final NaverUserFactory naverUserFactory;

    public SocialClient(KakaoUserFactory kakaoUserFactory, NaverUserFactory naverUserFactory) {
        this.kakaoUserFactory = kakaoUserFactory;
        this.naverUserFactory = naverUserFactory;
    }

    public OauthUserInfo getUserInfo(String code, String provider) {
        OauthUserFactory oauthUserFactory = null;

        if (provider.equals("Kakao")) {
            oauthUserFactory = kakaoUserFactory;
        }
        if (provider.equals("Naver")) {
            oauthUserFactory = naverUserFactory;
        }

        assert oauthUserFactory != null;
        return oauthUserFactory.getUserInfo(code);
    }
}
