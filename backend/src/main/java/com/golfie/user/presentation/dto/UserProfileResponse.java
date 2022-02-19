package com.golfie.user.presentation.dto;

import com.golfie.user.domain.User;
import lombok.Builder;

public class UserProfileResponse {
    private String id;
    private String nickname;
    private String email;
    private String imageUrl;
    private String ageRange;
    private String gender;
    private String job;
    private int averageHit;
    private int followerCount;
    private int followingCount;
    private int joinCount;
    private int feedCount;

    public UserProfileResponse() {
    }

    @Builder
    public UserProfileResponse(
            String id,
            String nickname,
            String email,
            String imageUrl,
            String ageRange,
            String gender,
            String job,
            int averageHit,
            int followerCount,
            int followingCount,
            int joinCount,
            int feedCount) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.imageUrl = imageUrl;
        this.ageRange = ageRange;
        this.gender = gender;
        this.job = job;
        this.averageHit = averageHit;
        this.followerCount = followerCount;
        this.followingCount = followingCount;
        this.joinCount = joinCount;
        this.feedCount = feedCount;
    }

    public static UserProfileResponse of(User user) {
        return UserProfileResponse.builder()
                .id(user.getId().toString())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .imageUrl(user.getImageUrl())
                .ageRange(user.getAgeRange().getSymbol())
                .gender(user.getGender().name())
                .job(user.getJob())
                .averageHit(user.getAverageHit())
                .followerCount(user.getFollowerCount())
                .followingCount(user.getFollowingCount())
                .joinCount(user.getAttendingCount())
                .feedCount(user.getFeeds().size())
                .build();
    }

    public String getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
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

    public int getFollowerCount() {
        return followerCount;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public int getJoinCount() {
        return joinCount;
    }

    public int getFeedCount() {
        return feedCount;
    }
}
