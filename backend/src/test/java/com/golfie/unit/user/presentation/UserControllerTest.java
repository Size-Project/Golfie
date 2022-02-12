package com.golfie.unit.user.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.golfie.auth.util.JwtTokenProvider;
import com.golfie.common.docs.DocumentationBase;
import com.golfie.common.exception.ApplicationExceptionDto;
import com.golfie.user.application.UserService;
import com.golfie.user.exception.DuplicatedNicknameException;
import com.golfie.user.presentation.UserController;
import com.golfie.user.presentation.dto.NicknameRequest;
import com.golfie.user.presentation.dto.UserProfileResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static com.golfie.common.exception.ErrorCode.DUPLICATE_NICKNAME;
import static com.golfie.common.exception.ErrorCode.NOT_AUTHENTICATED_USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @DisplayName("유저 자신의 프로필 정보를 가져온다.")
    @Test
    void request_User_Profile() throws Exception {
        //arrange
        String token = "Bearer " + jwtTokenProvider.createToken("1");

        UserProfileResponse userProfileResponse = UserProfileResponse.builder()
                .id("1")
                .nickname("test")
                .imageUrl("profileImageUrl")
                .email("test@test.com")
                .ageRange("20~29")
                .gender("MALE")
                .job("job")
                .averageHit(100)
                .build();

        given(userService.findUser(any())).willReturn(userProfileResponse);

        //act
        ResultActions result = mockMvc.perform(get("/api/users/me")
                .accept(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION, token));

        //assert
        String body = result.andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(body)
                .isEqualTo(objectMapper.writeValueAsString(userProfileResponse));

        verify(userService, times(1))
                .findUser(any());

        //docs
        result.andDo(document("user-me",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                responseFields(
                        fieldWithPath("id").type(STRING).description("id"),
                        fieldWithPath("nickname").type(STRING).description("닉네임"),
                        fieldWithPath("email").type(STRING).description("이메일"),
                        fieldWithPath("imageUrl").type(STRING).description("프로필 이미지"),
                        fieldWithPath("ageRange").type(STRING).description("연령대"),
                        fieldWithPath("gender").type(STRING).description("성별"),
                        fieldWithPath("job").type(STRING).description("직업"),
                        fieldWithPath("averageHit").type(NUMBER).description("평균타수"),
                        fieldWithPath("followerCount").type(NUMBER).description("팔로잉 수"),
                        fieldWithPath("followingCount").type(NUMBER).description("팔로우 수"),
                        fieldWithPath("joinCount").type(NUMBER).description("조인 수"),
                        fieldWithPath("feedCount").type(NUMBER).description("피드 수")
                )
        ));
    }

    @DisplayName("팔로우를 한다.")
    @Test
    void follow() throws Exception {
        //arrange
        String token = "Bearer " + jwtTokenProvider.createToken("1");
        long userId = 2L;

        doNothing().when(userService).follow(any(), any());

        //act
        ResultActions result = mockMvc.perform(post("/api/users/follow")
                .param("userId", String.valueOf(userId))
                .header(AUTHORIZATION, token));

        //assert
        result.andExpect(status().isOk());

        verify(userService, times(1))
                .follow(any(), any());

        //docs
        result.andDo(document("user-follow",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())
        ));
    }

    @DisplayName("팔로우를 취소한다.")
    @Test
    void unFollow() throws Exception {
        //arrange
        String token = "Bearer " + jwtTokenProvider.createToken("1");
        long userId = 2L;

        doNothing().when(userService).unFollow(any(), any());

        //act
        ResultActions result = mockMvc.perform(delete("/api/users/unfollow")
                .param("userId", String.valueOf(userId))
                .header(AUTHORIZATION, token));

        //assert
        result.andExpect(status().isOk());

        verify(userService, times(1))
                .unFollow(any(), any());

        //docs
        result.andDo(document("user-unfollow",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())
        ));
    }

    @DisplayName("비로그인 사용자는 팔로우 등록을 할 수 없다.")
    @Test
    void guestUser_Follow_Exception() throws Exception {
        //arrange
        String token = "Bearer guest";
        long userId = 2L;

        //act
        ResultActions result = mockMvc.perform(post("/api/users/follow")
                .param("userId", String.valueOf(userId))
                .header(AUTHORIZATION, token));

        //assert
        String body = result.andExpect(status().is4xxClientError())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(body).isEqualTo(objectMapper.writeValueAsString(ApplicationExceptionDto.of(NOT_AUTHENTICATED_USER)));

        verify(userService, never())
                .follow(any(), any());
    }

    @DisplayName("비로그인 사용자는 팔로우 취소를 할 수 없다.")
    @Test
    void guestUser_Unfollow_Exception() throws Exception {
        //arrange
        String token = "Bearer guest";
        long userId = 2L;

        //act
        ResultActions result = mockMvc.perform(delete("/api/users/unfollow")
                .param("userId", String.valueOf(userId))
                .header(AUTHORIZATION, token));

        //assert
        String body = result.andExpect(status().is4xxClientError())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(body).isEqualTo(objectMapper.writeValueAsString(ApplicationExceptionDto.of(NOT_AUTHENTICATED_USER)));

        verify(userService, never())
                .follow(any(), any());
    }

    @DisplayName("닉네임을 검증한 후 문제가 없으면 200 ok를 반환한다.")
    @Test
    void validate_Nickname_OK() throws Exception {
        //arrange
        NicknameRequest nicknameRequest = new NicknameRequest("junslee");

        //act
        ResultActions result = mockMvc.perform(post("/api/validate/nickname")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nicknameRequest)));

        //assert
        result.andExpect(status().isOk());

        //docs
        result.andDo(document("user-validate-nickname",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                        fieldWithPath("nickname").type(STRING).description("닉네임")
                )
        ));
    }

    @DisplayName("유효하지 않은 닉네임은 예외를 반환한다.")
    @Test
    void invalidate_Nickname_Exception() throws Exception {
        //arrange
        NicknameRequest nicknameRequest = new NicknameRequest("#@&junslee$@%");

        //act
        ResultActions result = mockMvc.perform(post("/api/validate/nickname")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nicknameRequest)));

        //assert
        String body = result.andExpect(status().is4xxClientError())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(body).isEqualTo(objectMapper.writeValueAsString(
                new ApplicationExceptionDto("U003", "유효하지 않은 닉네임입니다."))
        );

        //docs
        result.andDo(document("user-validate-nickname-invalidate",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                        fieldWithPath("nickname").type(STRING).description("닉네임")
                ),
                responseFields(
                        fieldWithPath("code").type(STRING).description("에러코드"),
                        fieldWithPath("message").type(STRING).description("에러메시지")
                )
        ));
    }

    @DisplayName("중복된 닉네임은 예외를 반환한다.")
    @Test
    void duplicate_User_Nickname_Exception() throws Exception {
        //arrange
        NicknameRequest nicknameRequest = new NicknameRequest("junslee");
        doThrow(new DuplicatedNicknameException(DUPLICATE_NICKNAME))
                .when(userService).validateNickname(any());

        //act
        ResultActions result = mockMvc.perform(post("/api/validate/nickname")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nicknameRequest)));

        //assert
        String body = result.andExpect(status().is4xxClientError())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(body).isEqualTo(objectMapper.writeValueAsString(
                new ApplicationExceptionDto("U002", "중복된 회원의 닉네임입니다."))
        );

        verify(userService, times(1))
                .validateNickname(any());

        //docs
        result.andDo(document("user-validate-nickname-duplicate",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                        fieldWithPath("nickname").type(STRING).description("닉네임")
                ),
                responseFields(
                        fieldWithPath("code").type(STRING).description("에러코드"),
                        fieldWithPath("message").type(STRING).description("에러메시지")
                )
        ));
    }

}
