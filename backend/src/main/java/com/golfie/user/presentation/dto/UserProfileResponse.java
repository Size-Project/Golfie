package com.golfie.user.presentation.dto;

import com.golfie.user.domain.User;
import lombok.Builder;

public class UserProfileResponse {
    private String nickname;
    private String email;
    private String ageRange;
    private String gender;

    public UserProfileResponse() {
    }

    @Builder
    public UserProfileResponse(String nickname, String email, String ageRange, String gender) {
        this.nickname = nickname;
        this.email = email;
        this.ageRange = ageRange;
        this.gender = gender;
    }


    public static UserProfileResponse of(User user) {
        return UserProfileResponse.builder()
                .nickname(user.getNickname())
                .email(user.getEmail())
                .ageRange(user.getAgeRange())
                .gender(user.getGender())
                .build();
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getAgeRange() {
        return ageRange;
    }

    public String getGender() {
        return gender;
    }
}
