package com.golfie.feed.presentation.dto;

import com.golfie.feed.domain.Feed;
import com.golfie.user.presentation.dto.UserProfileResponse;
import lombok.Builder;

import java.util.List;

public class FeedResponse {
    private UserProfileResponse author;
    private boolean following;
    private List<String> imageUrls;
    private String content;

    public FeedResponse() {
    }

    @Builder
    public FeedResponse(UserProfileResponse author, boolean following, List<String> imageUrls, String content) {
        this.author = author;
        this.following = following;
        this.imageUrls = imageUrls;
        this.content = content;
    }

    public static FeedResponse of(Feed feed, boolean isFollowing) {
        return FeedResponse.builder()
                .author(UserProfileResponse.of(feed.getAuthor()))
                .following(isFollowing)
                .imageUrls(feed.getImageUrls())
                .content(feed.getContent())
                .build();
    }

    public UserProfileResponse getAuthor() {
        return author;
    }

    public boolean isFollowing() {
        return following;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public String getContent() {
        return content;
    }
}
