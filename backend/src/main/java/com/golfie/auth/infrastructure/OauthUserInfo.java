package com.golfie.auth.infrastructure;

public interface OauthUserInfo {
    String getProviderId();
    String getEmail();
    String getAgeRange();
    String getGender();
}
