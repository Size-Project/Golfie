package com.golfie.unit.auth.presentation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.golfie.auth.application.AuthService;
import com.golfie.auth.application.dto.TokenDto;
import com.golfie.auth.exception.AlreadyRegisteredInUserException;
import com.golfie.auth.presentation.AuthController;
import com.golfie.auth.presentation.dto.LoginRequest;
import com.golfie.auth.presentation.dto.SignUpReadyRequest;
import com.golfie.auth.presentation.dto.SignUpReadyResponse;
import com.golfie.auth.presentation.dto.SignUpRequest;
import com.golfie.auth.util.JwtTokenProvider;
import com.golfie.common.docs.DocumentationBase;
import com.golfie.common.exception.ApplicationExceptionDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static com.golfie.common.exception.ErrorCode.ALREADY_REGISTERED_IN_USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({
        AuthController.class,
        JwtTokenProvider.class
})
class AuthControllerTest extends DocumentationBase {
    public static final String CODE = "CODE";
    public static final String PROVIDER_NAME = "TEST";
    public static final String JWT_TOKEN = "Bearer token";

    @MockBean
    private AuthService authService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @DisplayName("?????????")
    @Test
    void login() throws Exception {
        //arrange
        LoginRequest loginRequest = new LoginRequest(CODE, PROVIDER_NAME);
        TokenDto tokenDto = TokenDto.of(JWT_TOKEN);

        given(authService.login(any())).willReturn(tokenDto);

        //act
        ResultActions result = mockMvc.perform(post("/api/login/oauth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)));

        //assert
        String body = result.andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        verify(authService, times(1))
                .login(any());

        assertThat(body).isEqualTo(objectMapper.writeValueAsString(tokenDto));

        //docs
        result.andDo(document("social-login",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                        fieldWithPath("code").type(STRING).description("????????????"),
                        fieldWithPath("providerName").type(STRING).description("?????? ??????????????? ??????(KAKAO ?????? NAVER)")
                ),
                responseFields(
                        fieldWithPath("accessToken").type(STRING).description("??????")
                )
        ));
    }

    @DisplayName("???????????? ??????")
    @Test
    void prepare_Signup() throws Exception {
        //arrange
        SignUpReadyRequest signUpReadyRequest = new SignUpReadyRequest(CODE, PROVIDER_NAME);
        SignUpReadyResponse signUpReadyResponse = new SignUpReadyResponse(
                "test@test.com",
                "testImageUrl",
                "20-29",
                "MALE",
                "TEST"
        );
        given(authService.prepareSignUp(any())).willReturn(signUpReadyResponse);

        //act
        ResultActions result = mockMvc.perform(post("/api/signup/oauth/prepare")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpReadyRequest)));

        //assert
        String body = result.andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(body).isEqualTo(objectMapper.writeValueAsString(signUpReadyResponse));

        verify(authService, times(1))
                .prepareSignUp(any());

        //docs
        result.andDo(document("social-signup-ready",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                responseFields(
                        fieldWithPath("email").type(STRING).description("?????????"),
                        fieldWithPath("profileImage").type(STRING).description("??????????????????"),
                        fieldWithPath("ageRange").type(STRING).description("?????????"),
                        fieldWithPath("gender").type(STRING).description("??????"),
                        fieldWithPath("providerName").type(STRING).description("?????? ??????????????? ??????(KAKAO ?????? NAVER)")
                )
        ));
    }

    @DisplayName("???????????? ??????")
    @Test
    void signup_Complete() throws Exception {
        //arrange
        SignUpRequest signUpRequest = new SignUpRequest(
                "test@test.com",
                "testImageUrl",
                "20-29",
                "MALE",
                "TEST",
                "junslee",
                "job",
                100,
                "100-120",
                "20-29",
                "?????????"
        );
        TokenDto tokenDto = TokenDto.of(jwtTokenProvider.createToken("payload"));
        given(authService.signUp(any())).willReturn(tokenDto);

        //act
        ResultActions result = mockMvc.perform(post("/api/signup/oauth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpRequest)));

        //assert
        String body = result.andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(body).isEqualTo(objectMapper.writeValueAsString(tokenDto));

        verify(authService, times(1))
                .signUp(any());

        //docs
        result.andDo(document("social-signup-complete",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                        fieldWithPath("email").type(STRING).description("?????????"),
                        fieldWithPath("profileImage").type(STRING).description("?????? ?????? ????????? ?????????"),
                        fieldWithPath("ageRange").type(STRING).description("?????????"),
                        fieldWithPath("gender").type(STRING).description("??????"),
                        fieldWithPath("providerName").type(STRING).description("?????? ??????????????? ??????(KAKAO ?????? NAVER)"),
                        fieldWithPath("nickname").type(STRING).description("?????????"),
                        fieldWithPath("job").type(STRING).description("??????"),
                        fieldWithPath("averageHit").type(NUMBER).description("????????????"),
                        fieldWithPath("preferredHit").type(STRING).description("???????????? ?????? ?????? ??????"),
                        fieldWithPath("preferredAge").type(STRING).description("???????????? ?????? ?????????"),
                        fieldWithPath("preferredMood").type(STRING).description("???????????? ?????????")
                ),
                responseFields(
                        fieldWithPath("accessToken").type(STRING).description("??????")
                )
        ));
    }

    @DisplayName("?????? ????????? ????????? ?????? ????????? ????????????.")
    @Test
    void already_Registered_In_Exception() throws Exception {
        //arrange
        SignUpReadyRequest signUpReadyRequest = new SignUpReadyRequest(CODE, PROVIDER_NAME);
        doThrow(new AlreadyRegisteredInUserException(ALREADY_REGISTERED_IN_USER))
                .when(authService).prepareSignUp(any());

        //act
        ResultActions result = mockMvc.perform(post("/api/signup/oauth/prepare")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpReadyRequest)));

        //assert
        String body = result.andExpect(status().is4xxClientError())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ApplicationExceptionDto exceptionDto =
                new ApplicationExceptionDto("A002", "?????? ???????????? ?????? ??????????????????.");

        assertThat(body).isEqualTo(objectMapper.writeValueAsString(exceptionDto));

        //docs
        result.andDo(document("social-signup-validate",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                        fieldWithPath("code").type(STRING).description("????????????"),
                        fieldWithPath("providerName").type(STRING).description("?????? ??????????????? ??????(KAKAO ?????? NAVER)")
                ),
                responseFields(
                        fieldWithPath("code").type(STRING).description("????????????"),
                        fieldWithPath("message").type(STRING).description("???????????????")
                )
        ));
    }

    @DisplayName("???????????? ?????? ????????? ????????? ????????????.")
    @Test
    void too_Long_Job_Name_SignUpRequest() throws Exception {
        //arrange
        SignUpRequest signUpRequest = new SignUpRequest(
                "test@test.com",
                "testImageUrl",
                "20-29",
                "MALE",
                "TEST",
                "junslee",
                "????????????????????????????????????????????????????????????????????????????????????",
                100,
                "100-120",
                "20-29",
                "?????????"
        );
        TokenDto tokenDto = TokenDto.of(jwtTokenProvider.createToken("payload"));
        given(authService.signUp(any())).willReturn(tokenDto);

        //act
        ResultActions result = mockMvc.perform(post("/api/signup/oauth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpRequest)));

        //assert
        result.andExpect(status().is4xxClientError());
    }
}