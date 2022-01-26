package com.golfie.user.domain.profile;

import javax.persistence.Embeddable;

@Embeddable
public class BasicProfile {

    private String nickname;
    private String bio;

    public BasicProfile() {
    }

    public BasicProfile(String nickname, String bio) {
        this.nickname = nickname;
        this.bio = bio;
    }

    public String getNickname() {
        return nickname;
    }

    public String getBio() {
        return bio;
    }
}
