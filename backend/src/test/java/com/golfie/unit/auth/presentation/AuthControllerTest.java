package com.golfie.unit.auth.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.golfie.auth.application.AuthService;
import com.golfie.auth.application.dto.TokenDto;
import com.golfie.auth.presentation.AuthController;
import com.golfie.auth.presentation.dto.SocialLoginRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({
    AuthController.class
})
class AuthControllerTest {

    @MockBean
    private AuthService authService;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @DisplayName("소셜 로그인 - 로그인 성공 후 토큰을 반환한다.")
    @Test
    void socialLogin_Return_JwtToken() throws Exception {
        //arrange
        SocialLoginRequest socialLoginRequest = new SocialLoginRequest("CODE", "TEST");
        TokenDto tokenDto = TokenDto.of("jwtToken");

        given(authService.login(any())).willReturn(tokenDto);

        //act
        ResultActions result = mockMvc.perform(post("/api/login/oauth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(socialLoginRequest)));

        //assert
        String body = result.andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(body).isEqualTo(objectMapper.writeValueAsString(tokenDto));

        verify(authService, times(1))
                .login(any());
    }
}