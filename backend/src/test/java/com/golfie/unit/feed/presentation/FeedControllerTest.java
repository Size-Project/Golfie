package com.golfie.unit.feed.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.golfie.auth.util.JwtTokenProvider;
import com.golfie.common.docs.DocumentationBase;
import com.golfie.common.exception.ApplicationExceptionDto;
import com.golfie.common.fixture.TestUserInfo;
import com.golfie.feed.application.FeedService;
import com.golfie.feed.domain.Feed;
import com.golfie.feed.presentation.FeedController;
import com.golfie.feed.presentation.dto.FeedResponse;
import com.golfie.user.domain.User;
import com.golfie.user.domain.profile.BasicProfile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static com.golfie.common.exception.ErrorCode.NOT_AUTHENTICATED_USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest({
    FeedController.class,
    JwtTokenProvider.class
})
public class FeedControllerTest extends DocumentationBase {

    @MockBean
    private FeedService feedService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @DisplayName("새로운 피드를 생성한다.")
    @Test
    void create_New_Feed() throws Exception {
        //arrange
        String token = "Bearer " + jwtTokenProvider.createToken("1");
        given(feedService.save(any(), any())).willReturn(1L);

        //act
        ResultActions result = mockMvc.perform(multipart("/api/feeds")
                .file("feedImages", "This is my first feed image.".getBytes())
                .file("feedImages", "This is my second feed image.".getBytes())
                .param("content", "This is my feed.")
                .header(HttpHeaders.AUTHORIZATION, token)
        );

        //assert
        result.andExpect(status().isOk());

        verify(feedService, times(1))
                .save(any(), any());

        //docs
        result.andDo(document("feed-create",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestPartBody("feedImages")
        ));
    }

    @DisplayName("모든 피드를 조회한다.")
    @Test
    void find_All_Feeds() throws Exception {
        //arrange
        String token = "Bearer " + jwtTokenProvider.createToken("1");

        User user = new User(1L, new BasicProfile("authorName", "authorJob", 100),
                TestUserInfo.create().toSocialProfile());
        Feed feed1 = new Feed(user, List.of("url1.png", "url2.png"), "feed content");
        Feed feed2 = new Feed(user, List.of("url1.jpeg", "url2.jpeg"), "feed content");
        List<FeedResponse> feedResponses = List.of(
                FeedResponse.of(feed1, false, false),
                FeedResponse.of(feed2, false, false)
        );

        given(feedService.read(any(), any())).willReturn(feedResponses);

        //act
        ResultActions result = mockMvc.perform(get("/api/feeds")
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON));

        //assert
        result.andExpect(status().isOk());

        verify(feedService, times(1))
                .read(any(), any());

        //docs
        result.andDo(document("feed-read-all",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                responseFields(
                        fieldWithPath("[].author.id").type(STRING).description("id"),
                        fieldWithPath("[].author.nickname").type(STRING).description("닉네임"),
                        fieldWithPath("[].author.imageUrl").type(STRING).description("프로필 이미지"),
                        fieldWithPath("[].author.ageRange").type(STRING).description("연령대"),
                        fieldWithPath("[].author.gender").type(STRING).description("성별"),
                        fieldWithPath("[].author.job").type(STRING).description("직업"),
                        fieldWithPath("[].author.averageHit").type(NUMBER).description("평균 타수"),
                        fieldWithPath("[].imageUrls").type(ARRAY).description("피드 이미지"),
                        fieldWithPath("[].content").type(STRING).description("피드 내용"),
                        fieldWithPath("[].following").type(BOOLEAN).description("팔로우 여부"),
                        fieldWithPath("[].liking").type(BOOLEAN).description("좋아요 여부"),
                        fieldWithPath("[].likeCount").type(NUMBER).description("좋아요 수"),
                        fieldWithPath("[].createdAt").type(STRING).description("생성 날짜")
                )
        ));
    }

    @DisplayName("로그인하지 않은 사용자가 피드를 등록하면 예외를 반환한다.")
    @Test
    void guestUser_Create_Feed_Exception() throws Exception {
        //arrange
        String token = "Bearer guest";

        //act
        ResultActions result = mockMvc.perform(multipart("/api/feeds")
                .file("feedImages", "This is my first feed image.".getBytes())
                .file("feedImages", "This is my second feed image.".getBytes())
                .param("content", "This is my feed.")
                .header(HttpHeaders.AUTHORIZATION, token)
        );

        //assert
        String body = result.andExpect(status().is4xxClientError())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(body).isEqualTo(objectMapper.writeValueAsString(ApplicationExceptionDto.of(NOT_AUTHENTICATED_USER)));

        verify(feedService, never())
                .save(any(), any());

        //docs
        result.andDo(document("feed-create-guest-user-exception",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestPartBody("feedImages"),
                responseFields(
                        fieldWithPath("code").type(STRING).description("에러코드"),
                        fieldWithPath("message").type(STRING).description("에러메시지")
                )
        ));
    }

    @DisplayName("내 피드를 조회한다.")
    @Test
    void find_All_Of_My_Feeds() throws Exception {
        //arrange
        String token = "Bearer " + jwtTokenProvider.createToken("1");

        User user = new User(1L, new BasicProfile("authorName", "authorJob", 100),
                TestUserInfo.create().toSocialProfile());
        Feed feed1 = new Feed(user, List.of("url1.png", "url2.png"), "feed content");
        Feed feed2 = new Feed(user, List.of("url1.jpeg", "url2.jpeg"), "feed content");
        List<FeedResponse> feedResponses = List.of(
                FeedResponse.of(feed1, false, false),
                FeedResponse.of(feed2, false, false)
        );

        given(feedService.readMy(any(), any())).willReturn(feedResponses);

        //act
        ResultActions result = mockMvc.perform(get("/api/feeds/me")
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON));

        //assert
        result.andExpect(status().isOk());

        verify(feedService, times(1))
                .readMy(any(), any());

        //docs
        result.andDo(document("feed-read-all-me",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                responseFields(
                        fieldWithPath("[].author.id").type(STRING).description("id"),
                        fieldWithPath("[].author.nickname").type(STRING).description("닉네임"),
                        fieldWithPath("[].author.imageUrl").type(STRING).description("프로필 이미지"),
                        fieldWithPath("[].author.ageRange").type(STRING).description("연령대"),
                        fieldWithPath("[].author.gender").type(STRING).description("성별"),
                        fieldWithPath("[].author.job").type(STRING).description("직업"),
                        fieldWithPath("[].author.averageHit").type(NUMBER).description("평균 타수"),
                        fieldWithPath("[].imageUrls").type(ARRAY).description("피드 이미지"),
                        fieldWithPath("[].content").type(STRING).description("피드 내용"),
                        fieldWithPath("[].following").type(BOOLEAN).description("팔로우 여부"),
                        fieldWithPath("[].liking").type(BOOLEAN).description("좋아요 여부"),
                        fieldWithPath("[].likeCount").type(NUMBER).description("좋아요 수"),
                        fieldWithPath("[].createdAt").type(STRING).description("생성 날짜")
                )
        ));
    }

    @DisplayName("피드 좋아요를 등록한다.")
    @Test
    void do_Like_Feed() throws Exception {
        String token = "Bearer " + jwtTokenProvider.createToken("1");
        doNothing().when(feedService).doLike(any(), any());

        //act
        ResultActions result = mockMvc.perform(post("/api/feeds/like")
                .param("feedId", "1")
                .header(HttpHeaders.AUTHORIZATION, token));

        //assert
        result.andExpect(status().isOk());

        verify(feedService, times(1))
                .doLike(any(), any());

        //docs
        result.andDo(document("feed-like",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())
        ));
    }

    @DisplayName("피드 좋아요를 취소한다.")
    @Test
    void undo_Like_Feed() throws Exception {
        String token = "Bearer " + jwtTokenProvider.createToken("1");
        doNothing().when(feedService).undoLike(any(), any());

        //act
        ResultActions result = mockMvc.perform(delete("/api/feeds/like/undo")
                .param("feedId", "1")
                .header(HttpHeaders.AUTHORIZATION, token));

        //assert
        result.andExpect(status().isOk());

        verify(feedService, times(1))
                .undoLike(any(), any());

        //docs
        result.andDo(document("feed-unlike",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())
        ));
    }
}
