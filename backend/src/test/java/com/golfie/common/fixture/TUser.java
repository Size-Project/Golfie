package com.golfie.common.fixture;

import com.golfie.auth.application.dto.TokenDto;
import com.golfie.auth.presentation.dto.LoginRequest;
import com.golfie.auth.presentation.dto.SignUpRequest;
import com.golfie.feed.presentation.dto.FeedResponse;
import com.golfie.rounding.presentation.dto.RoundingResponse;
import com.golfie.rounding.presentation.dto.RoundingSaveRequest;
import com.golfie.user.presentation.dto.UserProfileResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.ValidatableResponse;
import org.springframework.http.MediaType;

import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

public class TUser {

    private final int port;
    private final Long id;
    private final String nickname;
    private String accessToken;

    public TUser(int port) {
        this(port, null, null);
    }

    public TUser(int port, Long id, String nickname) {
        this.port = port;
        this.id = id;
        this.nickname = nickname;
        this.accessToken = "Bearer guest";
    }

    public TUser signup() {
        accessToken = RestAssured
            .given()
                .port(port)
                .contentType(ContentType.JSON)
                .body(createSignUpRequest())
            .when()
                .request(Method.POST, "/api/signup/oauth")
            .then()
                .extract()
                .as(TokenDto.class)
                .getAccessToken();
        return this;
    }

    private SignUpRequest createSignUpRequest() {
        return new SignUpRequest(
                nickname + id + "@test.com",
                nickname + "ProfileImageUrl",
                "20-29",
                "MALE",
                "TEST",
                nickname + id,
                "job",
                100,
                "100-120",
                "20-29",
                "분위기"
        );
    }

    public TUser login() {
        accessToken = RestAssured
            .given()
                .port(port)
                .contentType(ContentType.JSON)
                .body(createLoginRequest())
            .when()
                .request(Method.POST, "/api/login/oauth")
            .then()
                .extract()
                .as(TokenDto.class)
                .getAccessToken();
        return this;
    }

    private LoginRequest createLoginRequest() {
        return new LoginRequest("CODE", "TEST");
    }

    public UserProfileResponse readProfile() {
        return RestAssured
                .given().log().all()
                    .port(port)
                    .auth()
                    .oauth2(accessToken)
                    .contentType(ContentType.JSON)
                .when()
                    .request(Method.GET, "/api/users/me")
                .then().log().all()
                    .extract()
                    .as(UserProfileResponse.class);
    }

    public TUser createFeed() {
        RestAssured
            .given().log().all()
                .port(port)
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .auth()
                .oauth2(accessToken)
                .multiPart("feedImages", new File(ClassLoader.getSystemResource("testImage.png").getFile()))
                .multiPart("content", "testContent")
            .when()
                .request(Method.POST, "/api/feeds")
            .then().log().all()
                .statusCode(200);
        return this;
    }

    public List<FeedResponse> readAllFeeds() {
        return RestAssured
                .given().log().all()
                    .port(port)
                    .contentType(ContentType.JSON)
                    .auth()
                    .oauth2(accessToken)
                .when()
                    .request(Method.GET, "/api/feeds?size=2")
                .then().log().all()
                    .statusCode(200)
                    .extract()
                    .body()
                    .jsonPath().getList(".", FeedResponse.class);
    }

    public List<FeedResponse> readAllMyFeeds() {
        return RestAssured
                .given().log().all()
                    .port(port)
                    .contentType(ContentType.JSON)
                    .auth()
                    .oauth2(accessToken)
                .when()
                    .request(Method.GET, "/api/feeds/me?size=2")
                .then().log().all()
                    .statusCode(200)
                    .extract()
                    .body()
                    .jsonPath().getList(".", FeedResponse.class);
    }

    public void follow(Long id) {
        RestAssured
            .given().log().all()
                .port(port)
                .auth()
                .oauth2(accessToken)
            .when()
                .request(Method.POST, "/api/users/follow?userId=" + id)
            .then().log().all()
                .statusCode(200);
    }

    public void unFollow(Long id) {
        RestAssured
            .given().log().all()
                .port(port)
                .auth()
                .oauth2(accessToken)
            .when()
                .request(Method.DELETE, "/api/users/unfollow?userId=" + id)
            .then().log().all()
                .statusCode(200);
    }

    public void likeFeed(Long id) {
        RestAssured
                .given().log().all()
                    .port(port)
                    .auth()
                    .oauth2(accessToken)
                .when()
                    .request(Method.POST, "/api/feeds/like?feedId=" + id)
                .then().log().all()
                    .statusCode(200);
    }

    public void unLikeFeed(Long id) {
        RestAssured
                .given().log().all()
                    .port(port)
                    .auth()
                    .oauth2(accessToken)
                .when()
                    .request(Method.DELETE, "/api/feeds/like/undo?feedId=" + id)
                .then().log().all()
                    .statusCode(200);
    }

    public void createRounding() {
        RoundingSaveRequest roundingSaveRequest = new RoundingSaveRequest(
            "courseName",
                "title",
                "content",
                10000,
                10,
                LocalDateTime.of(2022, 1, 1, 1, 1),
                "100-120",
                "20-29",
                "mood"
        );

        RestAssured
                .given().log().all()
                    .port(port)
                    .contentType(ContentType.JSON)
                    .auth()
                    .oauth2(accessToken)
                    .body(roundingSaveRequest)
                .when()
                    .request(Method.POST, "/api/roundings")
                .then().log().all()
                    .statusCode(200);
    }

    public List<RoundingResponse> readAllRoundings() {
        return RestAssured
                .given().log().all()
                    .port(port)
                    .contentType(ContentType.JSON)
                .when()
                    .request(Method.GET, "/api/roundings")
                .then().log().all()
                    .statusCode(200)
                    .extract()
                    .body()
                    .jsonPath().getList(".", RoundingResponse.class);
    }

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getAccessToken() {
        return accessToken;
    }

}
