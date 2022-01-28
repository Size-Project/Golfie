package com.golfie.user.domain;

import com.golfie.user.domain.profile.*;
import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private SocialProfile socialProfile;

    @Embedded
    private BasicProfile basicProfile;

    public User() {
    }

    public User(SocialProfile socialProfile) {
        this(null, new BasicProfile(), socialProfile);
    }

    public User(Long id, SocialProfile socialProfile) {
        this(id, new BasicProfile(), socialProfile);
    }

    public User(BasicProfile basicProfile, SocialProfile socialProfile) {
        this.basicProfile = basicProfile;
        this.socialProfile = socialProfile;
    }

    public User(Long id, BasicProfile basicProfile, SocialProfile socialProfile) {
        this.id = id;
        this.basicProfile = basicProfile;
        this.socialProfile = socialProfile;
    }

    public Long getId() {
        return id;
    }

    public ProviderName getProviderName() {
        return socialProfile.getProviderName();
    }

    public String getEmail() {
        return socialProfile.getEmail();
    }

    public String getImageUrl() {
        return socialProfile.getImageUrl();
    }

    public AgeRange getAgeRange() {
        return socialProfile.getAgeRange();
    }

    public Gender getGender() {
        return socialProfile.getGender();
    }

    public String getNickname() {
        return basicProfile.getNickname();
    }

    public String getBio() {
        return basicProfile.getBio();
    }

    public String toPayload() {
        return this.id.toString();
    }
}
