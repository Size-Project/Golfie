package com.golfie.rounding.presentation.dto;

import com.golfie.rounding.domain.Rounding;
import com.golfie.rounding.domain.course.Course;
import com.golfie.style.domain.Style;
import com.golfie.user.domain.User;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class RoundingSaveRequest {

    @NotNull
    private String courseName;
    @NotNull @Length(max = 50)
    private String title;
    @Length(max = 300)
    private String content;
    @NotNull
    private int price;
    @NotNull
    private int joinNum;
    @DateTimeFormat(pattern = "MM-dd'T'HH:mm:ss")
    private LocalDateTime dateTime;
    @NotNull
    private String preferredHit;
    @NotNull
    private String preferredAge;
    @NotNull
    private String preferredMood;

    public RoundingSaveRequest() {
    }

    public RoundingSaveRequest(
            String courseName,
            String title,
            String content,
            int price,
            int joinNum,
            LocalDateTime dateTime,
            String preferredHit,
            String preferredAge,
            String preferredMood)
    {
        this.courseName = courseName;
        this.title = title;
        this.content = content;
        this.price = price;
        this.joinNum = joinNum;
        this.dateTime = dateTime;
        this.preferredHit = preferredHit;
        this.preferredAge = preferredAge;
        this.preferredMood = preferredMood;
    }

    public String getCourseName() {
        return courseName;
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

    public String getPreferredHit() {
        return preferredHit;
    }

    public String getPreferredAge() {
        return preferredAge;
    }

    public String getPreferredMood() {
        return preferredMood;
    }

    public Rounding toRounding(Course course, User user, Style style) {
        return Rounding.builder()
                .course(course)
                .host(user)
                .title(title)
                .content(content)
                .price(price)
                .joinNum(joinNum)
                .dateTime(dateTime)
                .style(style)
                .build();
    }
}
