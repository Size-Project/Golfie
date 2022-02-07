package com.golfie.auth.infrastructure.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KakaoAccount {
    private String email;
    @JsonProperty("profile")
    private Profile profile;
    @JsonProperty("age_range")
    private String ageRange;
    private String gender;

    public String getEmail() {
        return email;
    }

    public String getProfileImage() { return profile.getProfileImageUrl(); }

    public String getAgeRange() {
        return ageRange.replace("~", "-");
    }

    public String getGender() {
        return gender;
    }

}
