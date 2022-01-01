package com.golfie.auth.presentation.dto;

import lombok.NoArgsConstructor;

public class SocialLoginRequest {
    private String code;

    private String provider;

    public SocialLoginRequest() {
    }

    public SocialLoginRequest(String code, String provider) {
        this.code = code;
        this.provider = provider;
    }

    public String getCode() {
        return code;
    }

    public String getProvider() {
        return provider;
    }
}
