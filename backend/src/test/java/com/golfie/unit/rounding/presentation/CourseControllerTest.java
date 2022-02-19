package com.golfie.unit.rounding.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.golfie.auth.util.JwtTokenProvider;
import com.golfie.common.docs.DocumentationBase;
import com.golfie.rounding.application.CourseService;
import com.golfie.rounding.presentation.CourseController;
import com.golfie.rounding.presentation.dto.CourseResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest({
        CourseController.class,
        JwtTokenProvider.class
})
public class CourseControllerTest extends DocumentationBase {

    @MockBean
    private CourseService courseService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @DisplayName("모든 골프장을 조회한다.")
    @Test
    void findAll_Courses() throws Exception {
        //arrange
        List<CourseResponse> courseResponses = List.of(
            new CourseResponse(1L, "course1", "address1"),
            new CourseResponse(2L, "course2", "address2")
        );

        given(courseService.findAll()).willReturn(courseResponses);

        //act
        ResultActions result = mockMvc.perform(get("/api/courses")
                .contentType(MediaType.APPLICATION_JSON));

        //assert
        String body = result.andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(body).isEqualTo(objectMapper.writeValueAsString(courseResponses));

        //docs
        result.andDo(document("course-find-all",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                responseFields(
                        fieldWithPath("[].id").type(NUMBER).description("골프장 id"),
                        fieldWithPath("[].name").type(STRING).description("골프장 이름"),
                        fieldWithPath("[].address").type(STRING).description("골프장 주소")
                )
        ));
    }
}
