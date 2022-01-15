package com.golfie.unit.user.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.golfie.auth.util.JwtTokenProvider;
import com.golfie.common.docs.DocumentationBase;
import com.golfie.user.application.UserService;
import com.golfie.user.presentation.UserController;
import com.golfie.user.presentation.dto.UserProfileResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({
    UserController.class,
    JwtTokenProvider.class
})
public class UserControllerTest extends DocumentationBase {
    private static final String AUTHORIZATION = "Authorization";

    @MockBean
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @DisplayName("유저의 프로필 정보를 가져온다.")
    @Test
    void request_User_Profile() throws Exception {
        //arrange
        String token = "Bearer " + jwtTokenProvider.createToken("1");

        UserProfileResponse userProfileResponse = UserProfileResponse.builder()
                .nickname("test")
                .email("test@test.com")
                .ageRange("20~29")
                .gender("male")
                .build();

        given(userService.findUser(any())).willReturn(userProfileResponse);

        //act
        ResultActions result = mockMvc.perform(get("/api/users/me")
                .accept(MediaType.APPLICATION_PROBLEM_JSON)
                .header(AUTHORIZATION, token));

        //assert
        String body = result.andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(body)
                .isEqualTo(objectMapper.writeValueAsString(userProfileResponse));

        //docs
        result.andDo(document("user-me",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                responseBody(),
                responseFields(
                        fieldWithPath("nickname").type(STRING).description("닉네임"),
                        fieldWithPath("email").type(STRING).description("이메일"),
                        fieldWithPath("ageRange").type(STRING).description("연령대"),
                        fieldWithPath("gender").type(STRING).description("성별")
                )
        ));
    }

}
