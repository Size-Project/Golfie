package com.golfie.auth.application.dto;

public class TokenDto {

    private String accessToken;

    public TokenDto() {
    }

    private TokenDto(String token) {
        this.accessToken = token;
    }

    public static TokenDto of(String token) {
        return new TokenDto(token);
    }

    public String getAccessToken() {
        return accessToken;
    }
}
