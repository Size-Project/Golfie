package com.golfie.auth.infrastructure.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.golfie.auth.infrastructure.OauthUserInfo;
import com.golfie.auth.presentation.dto.SignUpReadyResponse;
import com.golfie.user.domain.profile.AgeRange;
import com.golfie.user.domain.profile.Gender;
import com.golfie.user.domain.profile.ProviderName;
import com.golfie.user.domain.profile.SocialProfile;

import java.util.Arrays;
import java.util.Locale;

public class KakaoUserInfo implements OauthUserInfo {
    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @Override
    public ProviderName getProviderName() {
        return ProviderName.KAKAO;
    }

    @Override
    public String getEmail() {
        return kakaoAccount.getEmail();
    }

    @Override
    public String getProfileImage() {
        return kakaoAccount.getProfileImage();
    }

    @Override
    public String getAgeRange() {
        return kakaoAccount.getAgeRange();
    }

    @Override
    public String getGender() {
        return kakaoAccount.getGender();
    }

    public SocialProfile toSocialProfile() {
        return new SocialProfile(
            getProviderName(),
            getEmail(),
            getProfileImage(),
            Gender.valueOf(getGender().toUpperCase()),
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
