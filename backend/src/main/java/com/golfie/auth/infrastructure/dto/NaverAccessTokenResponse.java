package com.golfie.auth.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NaverAccessTokenResponse {

    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("expires_in")
    private Integer expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }
}
