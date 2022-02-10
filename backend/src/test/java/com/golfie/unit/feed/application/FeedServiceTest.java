package com.golfie.unit.feed.application;

import com.golfie.auth.presentation.dto.CurrentUser;
import com.golfie.auth.util.Authority;
import com.golfie.common.fixture.TestUserInfo;
import com.golfie.common.s3.S3Uploader;
import com.golfie.feed.application.FeedService;
import com.golfie.feed.domain.Feed;
import com.golfie.feed.domain.FeedRepository;
import com.golfie.feed.domain.like.Likes;
import com.golfie.feed.presentation.dto.FeedCreateRequest;
import com.golfie.feed.presentation.dto.FeedResponse;
import com.golfie.user.domain.User;
import com.golfie.user.domain.UserRepository;
import com.golfie.user.domain.profile.BasicProfile;
import com.golfie.user.domain.profile.SocialProfile;
import com.golfie.user.exception.UserNotFoundException;
import org.apache.http.entity.ContentType;
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
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.golfie.common.util.FileUtils.fileToMultipart;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

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
        URL resource = ClassLoader.getSystemResource("testImage.png");
        List<MultipartFile> mockMultipartFiles = List.of(
                fileToMultipart(new File(Objects.requireNonNull(resource).getFile()))
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
        User user = new User(1L, TestUserInfo.create().toSocialProfile());
        Feed feed1 = new Feed(user, List.of("url1", "url2"), "content");
        Feed feed2 = new Feed(user, List.of("url1", "url2"), "content");
        List<Feed> feedList = List.of(feed1, feed2);
        Slice<Feed> feeds = new SliceImpl<>(feedList);
        User loginUser = new User(TestUserInfo.create().toSocialProfile());

        given(feedRepository.findAllFeeds(any())).willReturn(feeds);
        given(userRepository.findById(1L)).willReturn(java.util.Optional.of(loginUser));

        //act
        List<FeedResponse> feedResponses =
                feedService.read(CurrentUser.of(1L, Authority.MEMBER), PageRequest.of(0, 2));

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

    @DisplayName("피드 좋아요를 등록한다.")
    @Test
    void do_Like_Feed() {
        //arrange
        BasicProfile basicProfile = new BasicProfile("junslee", "hello");
        SocialProfile socialProfile = TestUserInfo.create().toSocialProfile();
        User user = new User(1L, basicProfile, socialProfile);
        Feed feed = new Feed();

        given(userRepository.findById(any())).willReturn(Optional.of(user));
        given(feedRepository.findById(any())).willReturn(Optional.of(feed));

        //act
        feedService.doLike(CurrentUser.of(1L, Authority.MEMBER), 1L);

        //assert
        assertThat(feed.getLikeCount()).isEqualTo(1);

        verify(userRepository, times(1))
                .findById(any());
        verify(feedRepository, times(1))
                .findById(any());
    }

    @DisplayName("피드 좋아요를 취소한다.")
    @Test
    void undo_Like_Feed() {
        //arrange
        BasicProfile basicProfile = new BasicProfile("junslee", "hello");
        SocialProfile socialProfile = TestUserInfo.create().toSocialProfile();
        User user = new User(1L, basicProfile, socialProfile);
        Feed feed = new Feed();
        feed.doLike(Likes.of(feed, user));

        given(userRepository.findById(any())).willReturn(Optional.of(user));
        given(feedRepository.findById(any())).willReturn(Optional.of(feed));

        //act
        feedService.undoLike(CurrentUser.of(1L, Authority.MEMBER), 1L);

        //assert
        assertThat(feed.getLikeCount()).isEqualTo(0);

        verify(userRepository, times(1))
                .findById(any());
        verify(feedRepository, times(1))
                .findById(any());
    }

    @DisplayName("존재하지 않는 회원의 경우 예외를 반환한다.")
    @Test
    void user_Not_Found_Exception() throws IOException {
        //arrange
        URL resource = ClassLoader.getSystemResource("testImage.png");
        List<MultipartFile> mockMultipartFiles = List.of(
                fileToMultipart(new File(Objects.requireNonNull(resource).getFile()))
        );

        String content = "This is my feed.";
        FeedCreateRequest feedCreateRequest = new FeedCreateRequest(mockMultipartFiles, content);

        given(userRepository.findById(any())).willReturn(Optional.empty());

        //act and assert
        assertThrows(UserNotFoundException.class, () ->
                feedService.save(1L, feedCreateRequest)
        );

        verify(userRepository, times(1))
                .findById(any());
        verify(feedRepository, never())
                .save(any());
        verify(s3Uploader, never())
                .uploadFeedImages(any(), any());
    }
}
