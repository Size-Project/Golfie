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

    @DisplayName("?????? ????????? ????????? ????????? ????????????.")
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
                        fieldWithPath("nickname").type(STRING).description("?????????"),
                        fieldWithPath("email").type(STRING).description("?????????"),
                        fieldWithPath("imageUrl").type(STRING).description("????????? ?????????"),
                        fieldWithPath("ageRange").type(STRING).description("?????????"),
                        fieldWithPath("gender").type(STRING).description("??????"),
                        fieldWithPath("job").type(STRING).description("??????"),
                        fieldWithPath("averageHit").type(NUMBER).description("????????????"),
                        fieldWithPath("followerCount").type(NUMBER).description("????????? ???"),
                        fieldWithPath("followingCount").type(NUMBER).description("????????? ???"),
                        fieldWithPath("joinCount").type(NUMBER).description("?????? ???"),
                        fieldWithPath("feedCount").type(NUMBER).description("?????? ???")
                )
        ));
    }

    @DisplayName("???????????? ??????.")
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

    @DisplayName("???????????? ????????????.")
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

    @DisplayName("???????????? ???????????? ????????? ????????? ??? ??? ??????.")
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

    @DisplayName("???????????? ???????????? ????????? ????????? ??? ??? ??????.")
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

    @DisplayName("???????????? ????????? ??? ????????? ????????? 200 ok??? ????????????.")
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
                        fieldWithPath("nickname").type(STRING).description("?????????")
                )
        ));
    }

    @DisplayName("???????????? ?????? ???????????? ????????? ????????????.")
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
                new ApplicationExceptionDto("U003", "???????????? ?????? ??????????????????."))
        );

        //docs
        result.andDo(document("user-validate-nickname-invalidate",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                        fieldWithPath("nickname").type(STRING).description("?????????")
                ),
                responseFields(
                        fieldWithPath("code").type(STRING).description("????????????"),
                        fieldWithPath("message").type(STRING).description("???????????????")
                )
        ));
    }

    @DisplayName("????????? ???????????? ????????? ????????????.")
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
                new ApplicationExceptionDto("U002", "????????? ????????? ??????????????????."))
        );

        verify(userService, times(1))
                .validateNickname(any());

        //docs
        result.andDo(document("user-validate-nickname-duplicate",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                        fieldWithPath("nickname").type(STRING).description("?????????")
                ),
                responseFields(
                        fieldWithPath("code").type(STRING).description("????????????"),
                        fieldWithPath("message").type(STRING).description("???????????????")
                )
        ));
    }

}
