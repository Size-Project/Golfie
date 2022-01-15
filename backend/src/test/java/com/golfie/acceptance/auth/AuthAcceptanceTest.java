package com.golfie.acceptance.auth;

import com.golfie.acceptance.AcceptanceTest;
import com.golfie.auth.application.dto.TokenDto;
import com.golfie.auth.presentation.dto.SocialLoginRequest;
import com.golfie.user.domain.User;
import com.golfie.user.domain.UserRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthAcceptanceTest extends AcceptanceTest {

    @Autowired
    private UserRepository userRepository;

    private final SocialLoginRequest socialLoginRequest = new SocialLoginRequest("CODE", "TEST");

    @BeforeEach
    void setup() {
        userRepository.deleteAll();
    }

    @DisplayName("소셜 로그인 - 소셜 서비스 인증 후 토큰을 생성하여 반환한다.")
    @Test
    void socialLogin_Return_JwtToken() {
        TokenDto tokenResponse = RestAssured
                .given()
                    .port(port)
                    .accept(MediaType.APPLICATION_JSON_VALUE)
                    .contentType(ContentType.JSON)
                    .body(socialLoginRequest)
                .when()
                    .request(Method.POST, "/api/login/oauth")
                .then()
                    .extract()
                    .as(TokenDto.class);

        assertThat(tokenResponse.getAccessToken()).isNotBlank();
    }

    @DisplayName("소셜 로그인 - 소셜 서비스에서 받은 토큰을 이용하여 소셜 계정 개인 정보를 불러온다.")
    @Test
    void socialLogin_Return_User_Account_Profile() {
        TokenDto tokenResponse = RestAssured
                .given()
                    .port(port)
                    .accept(MediaType.APPLICATION_JSON_VALUE)
                    .contentType(ContentType.JSON)
                    .body(socialLoginRequest)
                .when()
                    .request(Method.POST, "/api/login/oauth")
                .then()
                    .extract()
                    .as(TokenDto.class);

        User user = userRepository.findByEmailAndProviderId("test@test.com", "12345678").get();

        User target = User.builder()
                .id(1L)
                .nickname("test")
                .email("test@test.com")
                .providerId("12345678")
                .ageRange("20~29")
                .gender("male")
                .build();

        assertThat(user)
                .usingRecursiveComparison()
                .isEqualTo(target);
    }

}
