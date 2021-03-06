package com.golfie.auth.presentation.dto;

import com.golfie.user.domain.profile.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

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
    @Length(max = 20)
    private String job;
    @Max(145) @Min(61)
    private int averageHit;

    @NotNull
    private String preferredHit;
    @NotNull
    private String preferredAge;
    @NotNull
    private String preferredMood;

    public SignUpRequest() {
    }

    public SignUpRequest(
            String email,
            String profileImage,
            String ageRange,
            String gender,
            String providerName,
            String nickname,
            String job,
            int averageHit,
            String preferredHit,
            String preferredAge,
            String preferredMood)
    {
        this.email = email;
        this.profileImage = profileImage;
        this.ageRange = ageRange;
        this.gender = gender;
        this.providerName = providerName;
        this.nickname = nickname;
        this.job = job;
        this.averageHit = averageHit;
        this.preferredHit = preferredHit;
        this.preferredAge = preferredAge;
        this.preferredMood = preferredMood;
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

    public String getJob() {
        return job;
    }

    public int getAverageHit() {
        return averageHit;
    }

    public String getPreferredHit() {
        return preferredHit;
    }

    public String getPreferredAge() {
        return preferredAge;
    }

    public String getPreferredMood() {
        return preferredMood;
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
        return new BasicProfile(nickname, job, averageHit);
    }
}
