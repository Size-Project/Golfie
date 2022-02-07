package com.golfie.auth.presentation.dto;

public class LoginRequest {

    private String code;
    private String providerName;

    public LoginRequest() {
    }

    public LoginRequest(String code, String providerName) {
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
