package com.golfie.feed.application;

import com.golfie.common.s3.S3Uploader;
import com.golfie.common.s3.StorageUploader;
import com.golfie.feed.domain.Feed;
import com.golfie.feed.domain.FeedRepository;
import com.golfie.feed.presentation.dto.FeedCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FeedService {

    private final FeedRepository feedRepository;
    private final StorageUploader s3Uploader;

    public Long save(Long userId, FeedCreateRequest feedCreateRequest) throws IOException {
        String content = feedCreateRequest.getContent();
        List<String> imageUrls = s3Uploader.uploadFeedImages(userId, feedCreateRequest.getFeedImages());

        Feed feed = new Feed(userId, imageUrls, content);
        return feedRepository.save(feed).getId();
    }
}
