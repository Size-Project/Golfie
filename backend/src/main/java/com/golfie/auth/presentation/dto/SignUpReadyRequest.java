package com.golfie.auth.presentation.dto;

public class SignUpReadyRequest {

    private String code;
    private String providerName;

    public SignUpReadyRequest() {
    }

    public SignUpReadyRequest(String code, String providerName) {
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
