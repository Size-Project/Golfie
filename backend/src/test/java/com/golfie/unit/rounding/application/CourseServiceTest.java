package com.golfie.unit.rounding.application;

import com.golfie.rounding.application.CourseService;
import com.golfie.rounding.domain.course.Course;
import com.golfie.rounding.domain.course.CourseRepository;
import com.golfie.rounding.presentation.dto.CourseResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

    @InjectMocks
    private CourseService courseService;

    @Mock
    private CourseRepository courseRepository;

    @DisplayName("모든 골프장을 조회한다.")
    @Test
    void findAll_Courses() {
        //arrange
        List<Course> courses = List.of(
                new Course(1L, "courseName1", "courseAddress1"),
                new Course(2L, "courseName2", "courseAddress2")
        );

        given(courseRepository.findAll()).willReturn(courses);

        //act
        List<CourseResponse> courseResponses = courseService.findAll();

        //assert
        assertThat(courseResponses)
                .extracting("name")
                .containsExactly("courseName1", "courseName2");
    }
}
