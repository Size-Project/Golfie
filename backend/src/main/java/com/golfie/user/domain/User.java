package com.golfie.user.domain;

import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String providerId;

    private String email;

    private String imageUrl;

    private String ageRange;

    private String gender;

    private int years;

    private int avgScore;

    private String message;

    public Long getId() {
        return id;
    }

    public String getProviderId() {
        return providerId;
    }

    public String getEmail() {
        return email;
    }

    public User() {
    }

    @Builder
    public User(Long id,
                String providerId,
                String email,
                String imageUrl,
                String ageRange,
                String gender,
                int years,
                int avgScore,
                String message)
    {
        this.id = id;
        this.providerId = providerId;
        this.email = email;
        this.imageUrl = imageUrl;
        this.ageRange = ageRange;
        this.gender = gender;
        this.years = years;
        this.avgScore = avgScore;
        this.message = message;
    }
}
