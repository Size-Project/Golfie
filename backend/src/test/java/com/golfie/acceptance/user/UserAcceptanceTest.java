package com.golfie.acceptance.user;

import com.golfie.acceptance.AcceptanceTest;
import com.golfie.auth.application.dto.TokenDto;
import com.golfie.auth.presentation.dto.SignUpRequest;
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
        //arrange
        SignUpRequest signUpRequest = new SignUpRequest(
                "test@test.com",
                "testImageUrl",
                "20-29",
                "MALE",
                "TEST",
                "junslee",
                "hello"
        );

        UserProfileResponse target = UserProfileResponse.builder()
                .nickname("junslee")
                .email("test@test.com")
                .gender("MALE")
                .ageRange("20-29")
                .imageUrl("testImageUrl")
                .build();

        //act
        TokenDto tokenResponse = RestAssured
                .given()
                    .port(port)
                    .accept(MediaType.APPLICATION_JSON_VALUE)
                    .contentType(ContentType.JSON)
                    .body(signUpRequest)
                .when()
                    .request(Method.POST, "/api/signup/oauth")
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

        //assert
        assertThat(userProfileResponse)
                .usingRecursiveComparison()
                .isEqualTo(target);
    }

}
