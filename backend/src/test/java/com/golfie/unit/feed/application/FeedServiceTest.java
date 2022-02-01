package com.golfie.unit.feed.application;

import com.golfie.auth.presentation.dto.LoginUser;
import com.golfie.auth.util.Authority;
import com.golfie.common.fixture.TestUserInfo;
import com.golfie.common.s3.S3Uploader;
import com.golfie.feed.application.FeedService;
import com.golfie.feed.domain.Feed;
import com.golfie.feed.domain.FeedRepository;
import com.golfie.feed.presentation.dto.FeedCreateRequest;
import com.golfie.feed.presentation.dto.FeedResponse;
import com.golfie.user.domain.User;
import com.golfie.user.domain.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class FeedServiceTest {

    @InjectMocks
    private FeedService feedService;

    @Mock
    private FeedRepository feedRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private S3Uploader s3Uploader;

    @DisplayName("피드 이미지를 s3 버킷에 업로드한다.")
    @Test
    void upload_Feed_Images_And_Save_A_Feed() throws IOException {
        //arrange
        List<MultipartFile> mockMultipartFiles = List.of(
                new MockMultipartFile("testImage.png", new FileInputStream("/Users/junslee/projects/Golfie/backend/src/test/resources/testImage.png"))
        );
        String content = "This is my feed.";
        FeedCreateRequest feedCreateRequest = new FeedCreateRequest(mockMultipartFiles, content);

        User user = new User(TestUserInfo.create().toSocialProfile());
        Feed feed = new Feed();

        given(userRepository.findById(any())).willReturn(java.util.Optional.of(user));
        given(feedRepository.save(any())).willReturn(feed);
        given(s3Uploader.uploadFeedImages(any(),any())).willReturn(List.of("imageUrl"));

        //act
        Long savedFeedId = feedService.save(1L, feedCreateRequest);

        //assert
        assertThat(savedFeedId).isEqualTo(feed.getId());

        verify(feedRepository, times(1))
                .save(any());
        verify(s3Uploader, times(1))
                .uploadFeedImages(any(), any());
    }

    @DisplayName("모든 피드를 조회한다.")
    @Test
    void read_All_Feeds() {
        User user = new User(TestUserInfo.create().toSocialProfile());
        Feed feed1 = new Feed(user, List.of("url1", "url2"), "content");
        Feed feed2 = new Feed(user, List.of("url1", "url2"), "content");
        List<Feed> feedList = List.of(feed1, feed2);
        Slice<Feed> feeds = new SliceImpl<>(feedList);
        User loginUser = new User(TestUserInfo.create().toSocialProfile());

        given(feedRepository.findAllFeeds(any())).willReturn(feeds);
        given(userRepository.findById(1L)).willReturn(java.util.Optional.of(loginUser));

        //act
        List<FeedResponse> feedResponses =
                feedService.read(new LoginUser(1L, Authority.MEMBER), PageRequest.of(0, 2));

        List<FeedResponse> target = List.of(
                FeedResponse.of(feed1, false),
                FeedResponse.of(feed2, false)
        );

        //assert
        assertThat(feedResponses)
                .usingRecursiveComparison()
                .isEqualTo(target);

        verify(feedRepository, times(1))
                .findAllFeeds(any());
        verify(userRepository, times(1))
                .findById(1L);
    }
}
