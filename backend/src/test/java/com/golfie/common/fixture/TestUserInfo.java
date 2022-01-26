package com.golfie.common.fixture;

import com.golfie.auth.infrastructure.OauthUserInfo;
import com.golfie.auth.presentation.dto.SignUpReadyResponse;
import com.golfie.user.domain.profile.AgeRange;
import com.golfie.user.domain.profile.Gender;
import com.golfie.user.domain.profile.ProviderName;
import com.golfie.user.domain.profile.SocialProfile;

import java.util.Arrays;

public class TestUserInfo implements OauthUserInfo {

    private final String email;
    private final String profileImage;
    private final String ageRange;
    private final String gender;

    private TestUserInfo(String email, String profileImage, String ageRange, String gender) {
        this.email = email;
        this.profileImage = profileImage;
        this.ageRange = ageRange;
        this.gender = gender;
    }

    public static TestUserInfo create() {
        return new TestUserInfo("test@test.com", "testImageUrl", "20-29", "MALE");
    }

    @Override
    public ProviderName getProviderName() {
        return ProviderName.TEST;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getProfileImage() {
        return profileImage;
    }

    @Override
    public String getAgeRange() {
        return ageRange;
    }

    @Override
    public String getGender() {
        return gender;
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
