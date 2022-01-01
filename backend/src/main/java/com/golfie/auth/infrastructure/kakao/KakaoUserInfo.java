package com.golfie.auth.infrastructure.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.golfie.auth.infrastructure.Oauth2UserInfo;

public class KakaoUserInfo implements Oauth2UserInfo {
    private String id;

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    public String getId() {
        return id;
    }

    public KakaoAccount getKakaoAccount() {
        return kakaoAccount;
    }

    @Override
    public String getProviderId() {
        return id;
    }

    @Override
    public String getEmail() {
        return kakaoAccount.getEmail();
    }
}
