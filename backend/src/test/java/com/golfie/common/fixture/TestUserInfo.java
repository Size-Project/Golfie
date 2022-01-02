package com.golfie.common.fixture;

import com.golfie.auth.infrastructure.OauthUserInfo;

public class TestUserInfo implements OauthUserInfo {

    private final String id;

    private TestUserInfo(String id) {
        this.id = id;
    }

    public static TestUserInfo of() {
        return new TestUserInfo("1234");
    }

    public String getId() {
        return id;
    }

    @Override
    public String getProviderId() {
        return id;
    }

    @Override
    public String getEmail() {
        return "test@test.com";
    }

    @Override
    public String getAgeRange() {
        return null;
    }

    @Override
    public String getGender() {
        return null;
    }
}
