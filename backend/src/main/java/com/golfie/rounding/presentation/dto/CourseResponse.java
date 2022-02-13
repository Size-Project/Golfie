package com.golfie.rounding.presentation.dto;

import com.golfie.rounding.domain.course.Course;

public class CourseResponse {

    private Long id;
    private String name;
    private String address;

    public CourseResponse() {
    }

    public CourseResponse(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public static CourseResponse of(Course course) {
        return new CourseResponse(course.getId(), course.getName(), course.getAddress());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
