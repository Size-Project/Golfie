package com.golfie.rounding.application;

import com.golfie.rounding.domain.course.CourseRepository;
import com.golfie.rounding.presentation.dto.CourseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public List<CourseResponse> findAll() {
        return courseRepository.findAll()
                .stream()
                .map(CourseResponse::of)
                .collect(toList());
    }
}
