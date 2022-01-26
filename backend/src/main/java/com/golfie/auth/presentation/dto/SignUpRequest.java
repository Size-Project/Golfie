package com.golfie.auth.presentation.dto;

import com.golfie.user.domain.profile.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class SignUpRequest {
    @Email
    private String email;
    private String profileImage;
    private String ageRange;
    private String gender;
    private String providerName;
    @NotBlank
    @Length(min = 2, max = 20)
    @Pattern(regexp = "^[가-힣a-zA-Z0-9]+$")
    private String nickname;
    @Length(max = 30)
    private String bio;

    public SignUpRequest() {
    }

    public SignUpRequest(
            String email,
            String profileImage,
            String ageRange,
            String gender,
            String providerName,
            String nickname,
            String bio)
    {
        this.email = email;
        this.profileImage = profileImage;
        this.ageRange = ageRange;
        this.gender = gender;
        this.providerName = providerName;
        this.nickname = nickname;
        this.bio = bio;
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

    public String getNickname() {
        return nickname;
    }

    public String getBio() {
        return bio;
    }

    public SocialProfile toSocialProfile() {
        return new SocialProfile(
            ProviderName.valueOf(providerName),
            email,
            profileImage,
            Gender.valueOf(gender),
            AgeRange.getValueBySymbol(ageRange)
        );
    }

    public BasicProfile toBasicProfile() {
        return new BasicProfile(nickname, bio);
    }
}
