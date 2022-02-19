package com.golfie.style.domain;

import com.golfie.rounding.domain.Rounding;
import com.golfie.user.domain.User;
import lombok.Builder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Style {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ageRange;

    private String averageHit;

    private String mood;

    @OneToMany(mappedBy = "style")
    private Set<Rounding> roundings;

    @OneToMany(mappedBy = "style")
    private Set<User> users;

    public Style() {
    }

    @Builder
    public Style(Long id, String ageRange, String averageHit, String mood) {
        this.id = id;
        this.ageRange = ageRange;
        this.averageHit = averageHit;
        this.mood = mood;
        this.roundings = new HashSet<>();
        this.users = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public String getAgeRange() {
        return ageRange;
    }

    public String getAverageHit() {
        return averageHit;
    }

    public String getMood() {
        return mood;
    }
}
