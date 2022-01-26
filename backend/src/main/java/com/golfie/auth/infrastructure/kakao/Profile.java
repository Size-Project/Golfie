package com.golfie.auth.infrastructure.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Profile {
    @JsonProperty("profile_image_url")
    private String profileImageUrl;

    public String getProfileImageUrl() {
        return profileImageUrl;
    }
}
