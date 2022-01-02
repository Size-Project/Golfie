package com.golfie.acceptance.auth;

import com.golfie.acceptance.AcceptanceTest;
import com.golfie.auth.presentation.dto.SocialLoginRequest;
import com.golfie.user.domain.User;
import com.golfie.user.domain.UserRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthAcceptanceTest extends AcceptanceTest {

    @Autowired
    private UserRepository userRepository;

    @DisplayName("소셜 로그인 - 성공")
    @Test
    void oauth_Login() {
        SocialLoginRequest socialLoginRequest = new SocialLoginRequest("code", "Test");

        ExtractableResponse<Response> response = RestAssured
                .given()
                    .port(port)
                    .contentType(ContentType.JSON)
                    .body(socialLoginRequest)
                .when()
                    .request(Method.POST, "/api/login/oauth")
                .then()
                    .extract();

        assertThat(response.body()).isNotNull();

        User user = userRepository.findByEmailAndProviderId("test@test.com", "1234").get();

        User target = User.builder()
                .id(1L)
                .email("test@test.com")
                .providerId("1234")
                .build();

        assertThat(user)
                .usingRecursiveComparison()
                .isEqualTo(target);
    }
}
