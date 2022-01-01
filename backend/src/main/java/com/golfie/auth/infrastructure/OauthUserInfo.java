package com.golfie.auth.infrastructure;

public interface Oauth2UserInfo {
    String getProviderId();
    String getEmail();
}
