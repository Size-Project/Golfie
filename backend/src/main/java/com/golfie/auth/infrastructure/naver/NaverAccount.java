package com.golfie.auth.infrastructure.naver;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NaverAccount {
    private String email;
    @JsonProperty("profile_image")
    private String profileImage;
    private String age;
    private String gender;

    public String getEmail() {
        return email;
    }

    public String getProfileImage() { return profileImage; }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }
}
