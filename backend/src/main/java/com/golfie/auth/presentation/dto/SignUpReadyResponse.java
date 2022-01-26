package com.golfie.auth.presentation.dto;

public class SignUpReadyResponse {

    private String email;
    private String profileImage;
    private String ageRange;
    private String gender;
    private String providerName;

    public SignUpReadyResponse() {
    }

    public SignUpReadyResponse(String email, String profileImage, String ageRange, String gender, String providerName) {
        this.email = email;
        this.profileImage = profileImage;
        this.ageRange = ageRange;
        this.gender = gender;
        this.providerName = providerName;
    }

    public String getEmail() {
        return email;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getAgeRange() {
        return ageRange;
    }

    public String getGender() {
        return gender;
    }

    public String getProviderName() {
        return providerName;
    }
}
