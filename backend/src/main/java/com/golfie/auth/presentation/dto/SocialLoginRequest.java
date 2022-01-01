package com.golfie.auth.presentation.dto;

public class SocialLoginRequest {

    private String code;
    private String providerName;

    public SocialLoginRequest() {
    }

    public SocialLoginRequest(String code, String providerName) {
        this.code = code;
        this.providerName = providerName;
    }

    public String getCode() {
        return code;
    }

    public String getProviderName() {
        return providerName;
    }
}
