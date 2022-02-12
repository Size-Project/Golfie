package com.golfie.feed.application;

import com.golfie.auth.presentation.dto.CurrentUser;
import com.golfie.user.domain.GuestUser;
import com.golfie.common.s3.StorageUploader;
import com.golfie.feed.domain.Feed;
import com.golfie.feed.domain.FeedRepository;
import com.golfie.feed.domain.like.Likes;
import com.golfie.feed.exception.FeedNotFoundException;
import com.golfie.feed.presentation.dto.FeedCreateRequest;
import com.golfie.feed.presentation.dto.FeedResponse;
import com.golfie.user.domain.User;
import com.golfie.user.domain.UserRepository;
import com.golfie.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.golfie.common.exception.ErrorCode.FEED_NOT_FOUND;
import static com.golfie.common.exception.ErrorCode.USER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class FeedService {

    private final FeedRepository feedRepository;
    private final UserRepository userRepository;
    private final StorageUploader s3Uploader;

    @Transactional
    public Long save(Long userId, FeedCreateRequest feedCreateRequest) throws IOException {
        User user = findUser(userId);
        List<String> imageUrls = s3Uploader.uploadFeedImages(userId, feedCreateRequest.getFeedImages());
        Feed feed = new Feed(user, imageUrls, feedCreateRequest.getContent());
        user.addFeed(feed);
        return feedRepository.save(feed).getId();
    }

    @Transactional(readOnly = true)
    public List<FeedResponse> read(CurrentUser currentUser, Pageable pageable) {
        Slice<Feed> feeds = feedRepository.findAllFeeds(pageable);
        User user = userRepository.findById(currentUser.getId())
                .orElseGet(GuestUser::new);

        return feeds.stream()
                .map(feed -> FeedResponse.of(feed, user.isFollowing(feed.getAuthor()), feed.isLikedBy(user)))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FeedResponse> readMy(CurrentUser currentUser, Pageable pageable) {
        User user = findUser(currentUser.getId());
        List<Feed> feeds = user.getFeeds();

        return feeds.stream()
                .map(feed -> FeedResponse.of(feed, user.isFollowing(feed.getAuthor()), feed.isLikedBy(user)))
                .collect(Collectors.toList());
    }

    @Transactional
    public void doLike(CurrentUser currentUser, Long feedId) {
        User user = findUser(currentUser.getId());
        Feed feed = findFeed(feedId);
        feed.doLike(Likes.of(feed, user));
    }

    @Transactional
    public void undoLike(CurrentUser currentUser, Long feedId) {
        User user = findUser(currentUser.getId());
        Feed feed = findFeed(feedId);
        feed.undoLike(user);
    }

    private Feed findFeed(Long id) {
        return feedRepository.findById(id)
                .orElseThrow(() -> new FeedNotFoundException(FEED_NOT_FOUND));
    }

    private User findUser(Long id) {
        return userRepository.findById(id)
                    .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
    }
}
