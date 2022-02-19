package com.golfie.unit.rounding.domain;

import com.golfie.rounding.domain.Rounding;
import com.golfie.rounding.domain.course.Course;
import com.golfie.style.domain.Style;
import com.golfie.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class RoundingTest {

    @DisplayName("참여자를 등록한다.")
    @Test
    void getAttendee() {
        User user = new User();
        Course course = new Course("courseName", "address");
        Style style = Style.builder().averageHit("100-120").ageRange("20-29").mood("mood").build();

        Rounding rounding = new Rounding(1L, course, style, user,
                "title", "content", 10000, 10,
                LocalDateTime.of(2022, 1, 1, 1, 1));

        rounding.addAttendee(user);
        assertThat(rounding.getAttendeeCount()).isEqualTo(1);
    }

}
