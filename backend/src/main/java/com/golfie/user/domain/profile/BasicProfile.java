package com.golfie.user.domain.profile;

import javax.persistence.Embeddable;

@Embeddable
public class BasicProfile {

    private String nickname;
    private String job;
    private Integer averageHit;

    public BasicProfile() {
    }

    public BasicProfile(String nickname, String job, Integer averageHit) {
        this.nickname = nickname;
        this.job = job;
        this.averageHit = averageHit;
    }

    public BasicProfile(String nickname) {
        this(nickname, null, null);
    }

    public String getNickname() {
        return nickname;
    }

    public String getJob() {
        return job;
    }

    public Integer getAverageHit() {
        return averageHit;
    }
}
