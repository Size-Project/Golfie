package com.golfie.unit.rounding.application;

import com.golfie.auth.presentation.dto.CurrentUser;
import com.golfie.auth.util.Authority;
import com.golfie.rounding.application.RoundingService;
import com.golfie.rounding.domain.Rounding;
import com.golfie.rounding.domain.RoundingRepository;
import com.golfie.rounding.domain.course.Course;
import com.golfie.rounding.domain.course.CourseRepository;
import com.golfie.rounding.presentation.dto.RoundingSaveRequest;
import com.golfie.style.common.StyleFinder;
import com.golfie.style.domain.Style;
import com.golfie.user.domain.User;
import com.golfie.user.domain.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class RoundingServiceTest {

    @InjectMocks
    private RoundingService roundingService;

    @Mock
    private RoundingRepository roundingRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private StyleFinder styleFinder;

    @DisplayName("라운딩을 등록한다.")
    @Test
    void save_Rounding() {
        //arrange
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

        User user = new User();
        Course course = new Course("courseName", "address");
        Style style = Style.builder()
                .averageHit("100-120")
                .ageRange("20-29")
                .mood("mood")
                .build();

        Rounding rounding = roundingSaveRequest.toRounding(course, user, style);

        given(userRepository.findById(any())).willReturn(Optional.of(user));
        given(courseRepository.findByName(any())).willReturn(Optional.of(course));
        given(styleFinder.findOrCreate(any(), any(), any())).willReturn(style);
        given(roundingRepository.save(any())).willReturn(rounding);

        //act
        Rounding target = roundingService.save(CurrentUser.of(1L, Authority.MEMBER), roundingSaveRequest);

        //assert
        assertThat(user.getAttendingCount()).isEqualTo(1);
        assertThat(rounding.getId()).isEqualTo(target.getId());
    }
}
