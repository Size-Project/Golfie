package com.golfie.auth.infrastructure.naver;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.golfie.auth.infrastructure.OauthUserInfo;

public class NaverUserInfo implements OauthUserInfo {

    @JsonProperty("resultcode")
    private String resultCode;
    private String message;
    @JsonProperty("response")
    private NaverAccount naverAccount;

    @Override
    public String getProviderId() {
        return naverAccount.getId();
    }

    @Override
    public String getEmail() {
        return naverAccount.getEmail();
    }

    @Override
    public String getAgeRange() {
        return naverAccount.getAge();
    }

    @Override
    public String getGender() {
        return naverAccount.getGender();
    }
}
