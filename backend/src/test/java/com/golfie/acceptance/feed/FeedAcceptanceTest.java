package com.golfie.acceptance.feed;

import com.golfie.acceptance.AcceptanceTest;
import com.golfie.auth.application.dto.TokenDto;
import com.golfie.auth.presentation.dto.SignUpRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import java.io.File;
import java.io.IOException;

public class FeedAcceptanceTest extends AcceptanceTest {

    @DisplayName("로그인된 유저는 피드를 등록할 수 있다.")
    @Test
    void save_New_Feed() throws IOException {
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

        //act and assert
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

        RestAssured
            .given().log().all()
                .port(port)
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .auth()
                .oauth2(tokenResponse.getAccessToken())
                .multiPart("feedImages", new File("/Users/junslee/projects/Golfie/backend/src/test/resources/testImage.png"))
                .multiPart("content", "content")
            .when()
                .request(Method.POST, "/api/feeds/save")
            .then()
                .statusCode(200);
    }

}
