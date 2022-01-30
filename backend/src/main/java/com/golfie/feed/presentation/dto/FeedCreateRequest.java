package com.golfie.feed.presentation.dto;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public class FeedCreateRequest {

    private List<MultipartFile> feedImages;
    private String content;

    public FeedCreateRequest() {
    }

    public FeedCreateRequest(List<MultipartFile> feedImages, String content) {
        this.feedImages = feedImages;
        this.content = content;
    }

    public List<MultipartFile> getFeedImages() {
        return feedImages;
    }

    public String getContent() {
        return content;
    }

    public void setFeedImages(List<MultipartFile> feedImages) {
        this.feedImages = feedImages;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
