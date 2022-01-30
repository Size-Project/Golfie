package com.golfie.feed.application;

import com.golfie.common.exception.ErrorCode;
import com.golfie.common.s3.S3Uploader;
import com.golfie.common.s3.StorageUploader;
import com.golfie.feed.domain.Feed;
import com.golfie.feed.domain.FeedRepository;
import com.golfie.feed.presentation.dto.FeedCreateRequest;
import com.golfie.user.domain.User;
import com.golfie.user.domain.UserRepository;
import com.golfie.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FeedService {

    private final FeedRepository feedRepository;
    private final UserRepository userRepository;
    private final StorageUploader s3Uploader;

    @Transactional
    public Long save(Long userId, FeedCreateRequest feedCreateRequest) throws IOException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND));

        List<String> imageUrls = s3Uploader.uploadFeedImages(userId, feedCreateRequest.getFeedImages());
        Feed feed = new Feed(user, imageUrls, feedCreateRequest.getContent());
        return feedRepository.save(feed).getId();
    }
}
