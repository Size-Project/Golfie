package com.golfie.unit.feed.domain;

import com.golfie.common.fixture.TestUserInfo;
import com.golfie.feed.domain.Feed;
import com.golfie.feed.domain.FeedRepository;
import com.golfie.feed.presentation.dto.FeedResponse;
import com.golfie.user.domain.User;
import com.golfie.user.domain.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class FeedRepositoryTest {

    @Autowired
    private FeedRepository feedRepository;

    @Autowired
    private UserRepository userRepository;

    @DisplayName("모든 피드를 조회한다.")
    @Test
    void findAllFeeds() {
        //arrange
        User user = userRepository.save(new User(TestUserInfo.create().toSocialProfile()));
        List<String> imageUrls = List.of("url1", "url2", "url3");
        String content = "This is my feed.";
        Feed saveFeed1 = feedRepository.save(new Feed(user, imageUrls, content));
        Feed saveFeed2 = feedRepository.save(new Feed(user, imageUrls, content));

        //act
        Slice<Feed> allFeeds = feedRepository.findAllFeeds(PageRequest.of(0, 2));

        List<FeedResponse> feedResponses = allFeeds.stream()
                .map(feed -> {
                    return FeedResponse.of(feed, false);
                })
                .collect(Collectors.toList());

        List<FeedResponse> target = List.of(
                FeedResponse.of(saveFeed1, false),
                FeedResponse.of(saveFeed2, false)
        );

        //assert
        assertThat(feedResponses.size()).isEqualTo(2);
        assertThat(feedResponses)
                .usingRecursiveComparison()
                .isEqualTo(target);
    }
}
