package com.golfie.auth.infrastructure;

import com.golfie.auth.presentation.dto.SignUpReadyResponse;
import com.golfie.user.domain.profile.ProviderName;
import com.golfie.user.domain.profile.SocialProfile;

public interface OauthUserInfo {
    ProviderName getProviderName();
    String getEmail();
    String getProfileImage();
    String getAgeRange();
    String getGender();
    SocialProfile toSocialProfile();
    SignUpReadyResponse toSignUpReadyResponse();
}
