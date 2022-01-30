package com.golfie.unit.feed.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.golfie.auth.util.JwtTokenProvider;
import com.golfie.common.docs.DocumentationBase;
import com.golfie.feed.application.FeedService;
import com.golfie.feed.presentation.FeedController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
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
}
