package com.golfie.rounding.presentation;

import com.golfie.rounding.application.CourseService;
import com.golfie.rounding.presentation.dto.CourseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/courses")
    public ResponseEntity<List<CourseResponse>> findAll() {
        List<CourseResponse> courseResponses = courseService.findAll();
        return ResponseEntity.ok(courseResponses);
    }

}
