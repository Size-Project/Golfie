package com.golfie.feed.application;

import com.golfie.auth.presentation.dto.CurrentUser;
import com.golfie.common.domain.GuestUser;
import com.golfie.common.s3.StorageUploader;
import com.golfie.feed.domain.Feed;
import com.golfie.feed.domain.FeedRepository;
import com.golfie.feed.presentation.dto.FeedCreateRequest;
import com.golfie.feed.presentation.dto.FeedResponse;
import com.golfie.user.domain.User;
import com.golfie.user.domain.UserRepository;
import com.golfie.user.domain.profile.BasicProfile;
import com.golfie.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.golfie.common.exception.ErrorCode.USER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class FeedService {

    private final FeedRepository feedRepository;
    private final UserRepository userRepository;
    private final StorageUploader s3Uploader;

    @Transactional
    public Long save(Long userId, FeedCreateRequest feedCreateRequest) throws IOException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));

        List<String> imageUrls = s3Uploader.uploadFeedImages(userId, feedCreateRequest.getFeedImages());
        Feed feed = new Feed(user, imageUrls, feedCreateRequest.getContent());
        return feedRepository.save(feed).getId();
    }

    @Transactional(readOnly = true)
    public List<FeedResponse> read(CurrentUser currentUser, Pageable pageable) {
        Slice<Feed> feeds = feedRepository.findAllFeeds(pageable);
        User user = userRepository.findById(currentUser.getId())
                .orElseGet(GuestUser::new);

        return feeds.stream()
                .map(feed -> FeedResponse.of(feed, user.isFollowing(feed.getAuthor())))
                .collect(Collectors.toList());
    }
}
