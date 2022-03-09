package com.golfie.rounding.presentation.dto;

import com.golfie.rounding.domain.Rounding;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class RoundingResponse {

    private CourseResponse course;
    private StyleDto style;
    private UserDto host;
    private List<UserDto> attendees;
    private String title;
    private String content;
    private int price;
    private int joinNum;
    private LocalDateTime dateTime;

    @Builder
    public RoundingResponse(
            CourseResponse course,
            StyleDto style,
            UserDto host,
            List<UserDto> attendees,
            String title,
            String content,
            int price,
            int joinNum,
            LocalDateTime dateTime) {
        this.course = course;
        this.style = style;
        this.host = host;
        this.attendees = attendees;
        this.title = title;
        this.content = content;
        this.price = price;
        this.joinNum = joinNum;
        this.dateTime = dateTime;
    }

    public static RoundingResponse of(Rounding rounding) {
        List<UserDto> attendees = rounding.getAttendee()
                .stream()
                .map(UserDto::of)
                .collect(Collectors.toList());

        return RoundingResponse.builder()
                .course(CourseResponse.of(rounding.getCourse()))
                .style(StyleDto.of(rounding.getStyle()))
                .host(UserDto.of(rounding.getHost()))
                .attendees(attendees)
                .title(rounding.getTitle())
                .content(rounding.getContent())
                .price(rounding.getPrice())
                .joinNum(rounding.getJoinNum())
                .dateTime(rounding.getDateTime())
                .build();
    }

    public CourseResponse getCourse() {
        return course;
    }

    public StyleDto getStyle() {
        return style;
    }

    public UserDto getHost() {
        return host;
    }

    public List<UserDto> getAttendees() {
        return attendees;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getPrice() {
        return price;
    }

    public int getJoinNum() {
        return joinNum;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

}
