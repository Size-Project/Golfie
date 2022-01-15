package com.golfie.common.fixture;

import com.golfie.auth.infrastructure.OauthUserInfo;

public class TestUserInfo implements OauthUserInfo {

    private final String id;
    private final String email;
    private final String ageRange;
    private final String gender;

    private TestUserInfo(String id, String email, String ageRange, String gender) {
        this.id = id;
        this.email = email;
        this.ageRange = ageRange;
        this.gender = gender;
    }

    public static TestUserInfo create() {
        return new TestUserInfo("12345678", "test@test.com", "20~29", "male");
    }

    @Override
    public String getProviderId() {
        return id;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getAgeRange() {
        return ageRange;
    }

    @Override
    public String getGender() {
        return gender;
    }
}
