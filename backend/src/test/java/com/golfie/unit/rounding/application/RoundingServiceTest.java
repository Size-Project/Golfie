package com.golfie.unit.rounding.application;

import com.golfie.auth.presentation.dto.CurrentUser;
import com.golfie.auth.util.Authority;
import com.golfie.common.fixture.TestUserInfo;
import com.golfie.rounding.application.RoundingService;
import com.golfie.rounding.domain.Rounding;
import com.golfie.rounding.domain.RoundingRepository;
import com.golfie.rounding.domain.course.Course;
import com.golfie.rounding.domain.course.CourseRepository;
import com.golfie.rounding.presentation.dto.RoundingResponse;
import com.golfie.rounding.presentation.dto.RoundingSaveRequest;
import com.golfie.style.common.StyleFinder;
import com.golfie.style.domain.Style;
import com.golfie.user.domain.User;
import com.golfie.user.domain.UserRepository;
import com.golfie.user.domain.profile.BasicProfile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

        verify(userRepository, times(1))
                .findById(any());
        verify(courseRepository, times(1))
                .findByName(any());
        verify(styleFinder, times(1))
                .findOrCreate(any(), any(), any());
        verify(roundingRepository, times(1))
                .save(any());
    }

    @DisplayName("모든 라운딩을 조회한다.")
    @Test
    void read_All_Roundings() {
        //arrange
        User host = new User(1L, new BasicProfile("hostName", "hostJob", 100),
                TestUserInfo.create().toSocialProfile());
        Course course = new Course(1L,"courseName", "address");
        Style style = Style.builder()
                .averageHit("100-120")
                .ageRange("20-29")
                .mood("mood")
                .build();
        Rounding rounding = Rounding.builder()
                .course(course)
                .style(style)
                .host(host)
                .title("roundingTitle")
                .content("roundingContent")
                .price(10000)
                .joinNum(4)
                .dateTime(LocalDateTime.now())
                .build();


        given(roundingRepository.findAll()).willReturn(List.of(rounding));

        //act
        List<RoundingResponse> target = roundingService.findAll();

        List<RoundingResponse> roundingResponses = List.of(
                RoundingResponse.of(rounding)
        );

        //assert
        assertThat(target)
                .usingRecursiveComparison()
                .isEqualTo(roundingResponses);

        verify(roundingRepository, times(1))
                .findAll();
    }

    @DisplayName("라운딩에 조인한다.")
    @Test
    void join_Rounding() {
        //arrange
        User user = new User(1L, new BasicProfile("hostName", "hostJob", 100),
                TestUserInfo.create().toSocialProfile());
        Course course = new Course(1L,"courseName", "address");
        Style style = Style.builder()
                .averageHit("100-120")
                .ageRange("20-29")
                .mood("mood")
                .build();
        Rounding rounding = Rounding.builder()
                .course(course)
                .style(style)
                .title("roundingTitle")
                .content("roundingContent")
                .price(10000)
                .joinNum(4)
                .dateTime(LocalDateTime.now())
                .build();

        given(userRepository.findById(any())).willReturn(Optional.of(user));
        given(roundingRepository.findById(any())).willReturn(Optional.of(rounding));

        //act
        roundingService.join(CurrentUser.of(1L, Authority.MEMBER), 1L);

        //assert
        verify(userRepository, times(1))
                .findById(any());
        verify(roundingRepository, times(1))
                .findById(any());
    }
}
