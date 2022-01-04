package com.golfie.auth.infrastructure.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KakaoAccount {
    private String email;
    @JsonProperty("age_range")
    private String ageRange;
    private String gender;

    public String getEmail() {
        return email;
    }

    public String getAgeRange() {
        return ageRange;
    }

    public String getGender() {
        return gender;
    }

}
