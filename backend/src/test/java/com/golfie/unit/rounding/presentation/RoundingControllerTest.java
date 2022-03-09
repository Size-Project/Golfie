package com.golfie.unit.rounding.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.golfie.auth.util.JwtTokenProvider;
import com.golfie.common.docs.DocumentationBase;
import com.golfie.common.fixture.TestUserInfo;
import com.golfie.rounding.application.RoundingService;
import com.golfie.rounding.domain.Rounding;
import com.golfie.rounding.domain.course.Course;
import com.golfie.rounding.domain.course.CourseRepository;
import com.golfie.rounding.presentation.RoundingController;
import com.golfie.rounding.presentation.dto.RoundingResponse;
import com.golfie.rounding.presentation.dto.RoundingSaveRequest;
import com.golfie.style.domain.Style;
import com.golfie.user.domain.User;
import com.golfie.user.domain.profile.BasicProfile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @DisplayName("모든 라운딩을 조회한다.")
    @Test
    void read_All_Roundings() throws Exception {
        //arrange
        User host = new User(1L, new BasicProfile("hostName", "hostJob", 100),
                TestUserInfo.create().toSocialProfile());
        Course course = new Course(1L,"courseName", "address");
        Style style = Style.builder()
                .averageHit("100-120")
                .ageRange("20-29")
                .mood("mood")
                .build();
        Rounding rounding = Rounding.builder()
                .course(course)
                .style(style)
                .host(host)
                .title("roundingTitle")
                .content("roundingContent")
                .price(10000)
                .joinNum(4)
                .dateTime(LocalDateTime.now())
                .build();

        User attendee1 = new User(2L, new BasicProfile("attendee1Name", "attendee1Job", 100),
                TestUserInfo.create().toSocialProfile());
        User attendee2 = new User(3L, new BasicProfile("attendee2Name", "attendee2Job", 100),
                TestUserInfo.create().toSocialProfile());

        rounding.addAttendee(attendee1);
        rounding.addAttendee(attendee2);

        List<RoundingResponse> roundingResponses = List.of(
                RoundingResponse.of(rounding)
        );

        given(roundingService.findAll()).willReturn(roundingResponses);

        //act
        ResultActions result = mockMvc.perform(get("/api/roundings")
                .contentType(MediaType.APPLICATION_JSON));

        //assert
        String body = result.andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

//        assertThat(body).isEqualTo(objectMapper.writeValueAsString(roundingResponses));

        //docs
        result.andDo(document("rounding-read-all",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                responseFields(
                        fieldWithPath("[].course.id").type(NUMBER).description("골프장 id"),
                        fieldWithPath("[].course.name").type(STRING).description("골프장 이름"),
                        fieldWithPath("[].course.address").type(STRING).description("골프장 위치"),
                        fieldWithPath("[].style.averageHit").type(STRING).description("함께하고 싶은 평균 타수"),
                        fieldWithPath("[].style.ageRange").type(STRING).description("함께하고 싶은 연령대"),
                        fieldWithPath("[].style.mood").type(STRING).description("좋아하는 분위기"),
                        fieldWithPath("[].host.id").type(STRING).description("호스트 id"),
                        fieldWithPath("[].host.nickname").type(STRING).description("호스트 닉네임"),
                        fieldWithPath("[].host.imageUrl").type(STRING).description("호스트 프로필 이미지"),
                        fieldWithPath("[].host.ageRange").type(STRING).description("호스트 연령대"),
                        fieldWithPath("[].host.gender").type(STRING).description("호스트 성별"),
                        fieldWithPath("[].host.job").type(STRING).description("호스트 직업"),
                        fieldWithPath("[].host.averageHit").type(NUMBER).description("호스트 평균 타수"),
                        fieldWithPath("[].attendees.[].id").type(STRING).description("참가자 id"),
                        fieldWithPath("[].attendees.[].nickname").type(STRING).description("참가자 닉네임"),
                        fieldWithPath("[].attendees.[].imageUrl").type(STRING).description("참가자 프로필 이미지"),
                        fieldWithPath("[].attendees.[].ageRange").type(STRING).description("참가자 연령대"),
                        fieldWithPath("[].attendees.[].gender").type(STRING).description("참가자 성별"),
                        fieldWithPath("[].attendees.[].job").type(STRING).description("참가자 직업"),
                        fieldWithPath("[].attendees.[].averageHit").type(NUMBER).description("참가자 평균 타수"),
                        fieldWithPath("[].title").type(STRING).description("제목"),
                        fieldWithPath("[].content").type(STRING).description("내용"),
                        fieldWithPath("[].price").type(NUMBER).description("가격"),
                        fieldWithPath("[].joinNum").type(NUMBER).description("인원"),
                        fieldWithPath("[].dateTime").type(STRING).description("일시 (yyyy-MM-dd'T'HH:mm:ss)")
                )
        ));
    }

    @DisplayName("라운딩에 조인한다.")
    @Test
    void join_Rounding() throws Exception {
        //arrange
        String token = "Bearer " + jwtTokenProvider.createToken("1");
        given(roundingService.join(any(), any())).willReturn(null);

        //act
        ResultActions result = mockMvc.perform(put("/api/roundings/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION, token));

        //assert
        String body = result.andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        //docs
        result.andDo(document("rounding-join",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())
        ));
    }
}
