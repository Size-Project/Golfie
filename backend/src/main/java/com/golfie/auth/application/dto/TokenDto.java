package com.golfie.auth.application.dto;

public class TokenDto {

    private final String accessToken;

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
