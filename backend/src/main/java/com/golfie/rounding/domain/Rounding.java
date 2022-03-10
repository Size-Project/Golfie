package com.golfie.rounding.domain;

import com.golfie.rounding.domain.course.Course;
import com.golfie.style.domain.Style;
import com.golfie.user.domain.User;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Rounding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "style_id")
    private Style style;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "host_id")
    private User host;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<User> attendee;

    private String title;

    private String content;

    private int price;

    private int joinNum;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateTime;

    public Rounding() {
    }

    @Builder
    public Rounding(
            Long id,
            Course course,
            Style style,
            User host,
            String title,
            String content,
            int price,
            int joinNum,
            LocalDateTime dateTime)
    {
        this.id = id;
        this.course = course;
        this.style = style;
        this.host = host;
        this.attendee = new HashSet<>();
        this.title = title;
        this.content = content;
        this.price = price;
        this.joinNum = joinNum;
        this.dateTime = dateTime;
    }

    public void addAttendee(User user) {
        attendee.add(user);
    }

    public int getAttendeeCount() {
        return attendee.size();
    }

    public Long getId() {
        return id;
    }

    public Course getCourse() {
        return course;
    }

    public Style getStyle() {
        return style;
    }

    public User getHost() {
        return host;
    }

    public Set<User> getAttendee() {
        return attendee;
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
