package com.golfie.unit.feed.application;

import com.golfie.common.s3.S3Uploader;
import com.golfie.feed.application.FeedService;
import com.golfie.feed.domain.Feed;
import com.golfie.feed.domain.FeedRepository;
import com.golfie.feed.presentation.dto.FeedCreateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
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
    private S3Uploader s3Uploader;

    @DisplayName("피드 이미지를 s3 버킷에 업로드한다.")
    @Test
    void upload_Feed_Images_And_Save_A_Feed() throws IOException {
        //arrange
        List<MultipartFile> mockMultipartFiles = Arrays.asList(
                new MockMultipartFile("ec2.jpeg", new FileInputStream("/Users/junslee/projects/Golfie/backend/src/test/resources/ec2.jpeg")),
                new MockMultipartFile("testImage.png", new FileInputStream("/Users/junslee/projects/Golfie/backend/src/test/resources/testImage.png"))
        );

        String content = "This is my feed.";
        FeedCreateRequest feedCreateRequest = new FeedCreateRequest(mockMultipartFiles, content);

        Feed feed = new Feed();

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
}
