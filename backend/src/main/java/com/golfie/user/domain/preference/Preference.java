package com.golfie.user.domain.preference;

import javax.persistence.Embeddable;

@Embeddable
public class Preference {

    private String preferredHit;
    private String preferredAge;
    private String preferredMood;

    public Preference() {
    }

    public Preference(String preferredHit, String preferredAge, String preferredMood) {
        this.preferredHit = preferredHit;
        this.preferredAge = preferredAge;
        this.preferredMood = preferredMood;
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
}
