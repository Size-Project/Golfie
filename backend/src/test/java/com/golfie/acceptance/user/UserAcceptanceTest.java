package com.golfie.acceptance.user;

import com.golfie.acceptance.AcceptanceTest;
import com.golfie.auth.application.dto.TokenDto;
import com.golfie.auth.presentation.dto.SocialLoginRequest;
import com.golfie.user.presentation.dto.UserProfileResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

public class UserAcceptanceTest extends AcceptanceTest {

    @DisplayName("로그인된 사용자의 프로필 정보를 조회한다.")
    @Test
    void find_Logged_In_User_Profile() {
        final SocialLoginRequest socialLoginRequest =
                new SocialLoginRequest("CODE", "TEST");

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

        UserProfileResponse userProfileResponse = RestAssured
                .given()
                    .port(port)
                    .auth()
                    .oauth2(tokenResponse.getAccessToken())
                    .contentType(ContentType.JSON)
                .when()
                    .request(Method.GET, "/api/users/me")
                .then()
                    .extract()
                    .as(UserProfileResponse.class);

        UserProfileResponse target = UserProfileResponse.builder()
                .nickname("test")
                .email("test@test.com")
                .ageRange("20~29")
                .gender("male")
                .build();

        assertThat(userProfileResponse)
                .usingRecursiveComparison()
                .isEqualTo(target);
    }

}
