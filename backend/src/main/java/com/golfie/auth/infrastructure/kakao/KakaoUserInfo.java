package com.golfie.auth.infrastructure.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.golfie.auth.infrastructure.OauthUserInfo;

public class KakaoUserInfo implements OauthUserInfo {
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

    @Override
    public String getAgeRange() {
        return kakaoAccount.getAgeRange();
    }

    @Override
    public String getGender() {
        return kakaoAccount.getGender();
    }
}
