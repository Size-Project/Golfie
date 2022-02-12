package com.golfie.user.presentation.dto;

import com.golfie.user.domain.User;
import lombok.Builder;

public class AuthorResponse {
    private String id;
    private String nickname;
    private String imageUrl;
    private String ageRange;
    private String gender;
    private String job;
    private int averageHit;

    public AuthorResponse() {
    }

    @Builder
    public AuthorResponse(
            String id,
            String nickname,
            String email,
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

    public static AuthorResponse of(User user) {
        return AuthorResponse.builder()
                .id(user.getId().toString())
                .nickname(user.getNickname())
                .email(user.getEmail())
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
