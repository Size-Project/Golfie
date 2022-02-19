package com.golfie.unit.rounding.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.golfie.auth.util.JwtTokenProvider;
import com.golfie.common.docs.DocumentationBase;
import com.golfie.rounding.application.RoundingService;
import com.golfie.rounding.domain.course.Course;
import com.golfie.rounding.domain.course.CourseRepository;
import com.golfie.rounding.presentation.RoundingController;
import com.golfie.rounding.presentation.dto.RoundingSaveRequest;
import com.golfie.style.domain.Style;
import com.golfie.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;

@WebMvcTest({
        RoundingController.class,
        JwtTokenProvider.class,
        CourseRepository.class
})
public class RoundingControllerTest extends DocumentationBase {
    private static final String AUTHORIZATION = "Authorization";

    @MockBean
    private RoundingService roundingService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @DisplayName("라운딩을 등록한다.")
    @Test
    void save_Rounding() throws Exception {
        //arrange
        String token = "Bearer " + jwtTokenProvider.createToken("1");

        RoundingSaveRequest roundingSaveRequest = new RoundingSaveRequest(
                "courseName",
                "title",
                "content",
                10000,
                10,
                LocalDateTime.of(2022, 1, 1, 1, 1),
                "100-120",
                "20-29",
                "mood"
        );

        User user = new User();
        Course course = new Course("courseName", "address");
        Style style = Style.builder()
                .averageHit("100-120")
                .ageRange("20-29")
                .mood("mood")
                .build();

        given(roundingService.save(any(), any()))
                .willReturn(roundingSaveRequest.toRounding(course, user, style));

        //act
        ResultActions result = mockMvc.perform(post("/api/roundings")
                .contentType(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION, token)
                .content(objectMapper.registerModule(new JavaTimeModule())
                        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                        .writeValueAsString(roundingSaveRequest)));

        //assert
        result.andExpect(status().isOk());

        //docs
        result.andDo(document("rounding-save",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                        fieldWithPath("courseName").type(STRING).description("골프장 이름"),
                        fieldWithPath("title").type(STRING).description("제목"),
                        fieldWithPath("content").type(STRING).description("내용"),
                        fieldWithPath("price").type(NUMBER).description("가격"),
                        fieldWithPath("joinNum").type(NUMBER).description("인원"),
                        fieldWithPath("dateTime").type(STRING).description("일시 (yyyy-MM-dd'T'HH:mm:ss)"),
                        fieldWithPath("preferredHit").type(STRING).description("함께하고 싶은 평균 타수"),
                        fieldWithPath("preferredAge").type(STRING).description("함께하고 싶은 연령대"),
                        fieldWithPath("preferredMood").type(STRING).description("좋아하는 분위기")
                )
        ));
    }
}
