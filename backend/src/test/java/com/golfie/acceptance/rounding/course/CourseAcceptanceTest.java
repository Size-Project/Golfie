package com.golfie.acceptance.rounding.course;

import com.golfie.acceptance.AcceptanceTest;
import com.golfie.feed.presentation.dto.FeedResponse;
import com.golfie.rounding.domain.course.Course;
import com.golfie.rounding.presentation.dto.CourseResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CourseAcceptanceTest extends AcceptanceTest {

    @BeforeEach
    void setup() {
        courseRepository.save(new Course("courseName1", "address1"));
        courseRepository.save(new Course("courseName2", "address2"));
    }

    @DisplayName("모든 골프장을 조회한다.")
    @Test
    void find_All_Courses() {
        List<CourseResponse> courseResponses = RestAssured
                .given().log().all()
                    .port(port)
                    .contentType(ContentType.JSON)
                .when()
                    .request(Method.GET, "/api/courses")
                .then().log().all()
                    .statusCode(200)
                    .extract()
                    .jsonPath().getList(".", CourseResponse.class);

        assertThat(courseResponses)
                .hasSize(2)
                .extracting("name")
                .containsExactly("courseName1", "courseName2");
    }
}
