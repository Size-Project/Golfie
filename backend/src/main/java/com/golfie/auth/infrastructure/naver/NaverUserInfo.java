package com.golfie.auth.infrastructure.naver;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.golfie.auth.infrastructure.OauthUserInfo;
import com.golfie.auth.presentation.dto.SignUpReadyResponse;
import com.golfie.user.domain.profile.AgeRange;
import com.golfie.user.domain.profile.Gender;
import com.golfie.user.domain.profile.ProviderName;
import com.golfie.user.domain.profile.SocialProfile;

import java.util.Arrays;

public class NaverUserInfo implements OauthUserInfo {

    @JsonProperty("resultcode")
    private String resultCode;
    private String message;
    @JsonProperty("response")
    private NaverAccount naverAccount;

    @Override
    public ProviderName getProviderName() {
        return ProviderName.NAVER;
    }

    @Override
    public String getEmail() {
        return naverAccount.getEmail();
    }

    @Override
    public String getProfileImage() {
        return naverAccount.getProfileImage();
    }

    @Override
    public String getAgeRange() {
        return naverAccount.getAge();
    }

    @Override
    public String getGender() {
        if (naverAccount.getGender().equals("F")) {
            return "FEMALE";
        }
        return "MALE";
    }

    public SocialProfile toSocialProfile() {
        return new SocialProfile(
            getProviderName(),
            getEmail(),
            getProfileImage(),
            Gender.valueOf(getGender()),
            Arrays.stream(AgeRange.values())
                    .filter(value -> value.getSymbol().equals(getAgeRange()))
                    .findFirst()
                    .get()
        );
    }

    public SignUpReadyResponse toSignUpReadyResponse() {
        return new SignUpReadyResponse(
                getEmail(),
                getProfileImage(),
                getAgeRange(),
                Gender.valueOf(getGender().toUpperCase()).name(),
                getProviderName().name()
        );
    }
}
