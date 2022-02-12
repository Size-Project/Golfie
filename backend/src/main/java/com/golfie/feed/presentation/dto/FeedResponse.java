package com.golfie.feed.presentation.dto;

import com.golfie.feed.domain.Feed;
import com.golfie.user.presentation.dto.AuthorResponse;
import lombok.Builder;
import java.time.LocalDateTime;
import java.util.List;

public class FeedResponse {
    private AuthorResponse author;
    private boolean following;
    private boolean liking;
    private List<String> imageUrls;
    private String content;
    private int likeCount;
    private LocalDateTime createdAt;

    public FeedResponse() {
    }

    @Builder
    public FeedResponse(
            AuthorResponse author,
            boolean following,
            boolean liking,
            List<String> imageUrls,
            String content,
            int likeCount,
            LocalDateTime createdAt
    ) {
        this.author = author;
        this.following = following;
        this.liking = liking;
        this.imageUrls = imageUrls;
        this.content = content;
        this.likeCount = likeCount;
        this.createdAt = createdAt;
    }

    public static FeedResponse of(Feed feed, boolean isFollowing, boolean isLiking) {
        return FeedResponse.builder()
                .author(AuthorResponse.of(feed.getAuthor()))
                .following(isFollowing)
                .liking(isLiking)
                .imageUrls(feed.getImageUrls())
                .content(feed.getContent())
                .likeCount(feed.getLikeCount())
                .createdAt(feed.getCreatedAt())
                .build();
    }

    public AuthorResponse getAuthor() {
        return author;
    }

    public boolean isFollowing() {
        return following;
    }

    public boolean isLiking() {
        return liking;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public String getContent() {
        return content;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
