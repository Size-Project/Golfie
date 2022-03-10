package com.golfie.rounding.presentation.dto;

import com.golfie.user.domain.User;
import lombok.Builder;

public class UserDto {

    private String id;
    private String nickname;
    private String imageUrl;
    private String ageRange;
    private String gender;
    private String job;
    private int averageHit;

    public UserDto() {
    }

    @Builder
    public UserDto(
            String id,
            String nickname,
            String imageUrl,
            String ageRange,
            String gender,
            String job,
            int averageHit) {
        this.id = id;
        this.nickname = nickname;
        this.imageUrl = imageUrl;
        this.ageRange = ageRange;
        this.gender = gender;
        this.job = job;
        this.averageHit = averageHit;
    }

    public static UserDto of(User user) {
        return UserDto.builder()
                .id(user.getId().toString())
                .nickname(user.getNickname())
                .imageUrl(user.getImageUrl())
                .ageRange(user.getAgeRange().getSymbol())
                .gender(user.getGender().name())
                .job(user.getJob())
                .averageHit(user.getAverageHit())
                .build();
    }

    public String getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getAgeRange() {
        return ageRange;
    }

    public String getGender() {
        return gender;
    }

    public String getJob() {
        return job;
    }

    public int getAverageHit() {
        return averageHit;
    }
}
