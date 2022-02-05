package com.golfie.unit.feed.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.golfie.auth.util.JwtTokenProvider;
import com.golfie.common.docs.DocumentationBase;
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
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
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
        ResultActions result = mockMvc.perform(multipart("/api/feeds/save")
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

        User user = new User(new BasicProfile("authorName", "This is my profile."), TestUserInfo.create().toSocialProfile());
        Feed feed1 = new Feed(user, List.of("url1.png", "url2.png"), "feed content");
        Feed feed2 = new Feed(user, List.of("url1.jpeg", "url2.jpeg"), "feed content");
        List<FeedResponse> feedResponses = List.of(
                FeedResponse.of(feed1, false),
                FeedResponse.of(feed2, false)
        );

        given(feedService.read(any(), any())).willReturn(feedResponses);

        //act
        ResultActions result = mockMvc.perform(get("/api/feeds")
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON));

        //assert
        String body = result.andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(body).isEqualTo(objectMapper.writeValueAsString(feedResponses));

        verify(feedService, times(1))
                .read(any(), any());

        //docs
        result.andDo(document("feed-read-all",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                responseFields(
                        fieldWithPath("[].author.nickname").type(STRING).description("닉네임"),
                        fieldWithPath("[].author.email").type(STRING).description("이메일"),
                        fieldWithPath("[].author.imageUrl").type(STRING).description("프로필이미지"),
                        fieldWithPath("[].author.ageRange").type(STRING).description("연령대"),
                        fieldWithPath("[].author.gender").type(STRING).description("성별"),
                        fieldWithPath("[].imageUrls").type(ARRAY).description("피드이미지"),
                        fieldWithPath("[].content").type(STRING).description("피드내용"),
                        fieldWithPath("[].following").type(BOOLEAN).description("팔로우여부")
                )
        ));
    }
}
