package com.golfie.user.domain.profile;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class SocialProfile {
    @Enumerated(EnumType.STRING)
    private ProviderName providerName;

    private String email;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private AgeRange ageRange;

    public SocialProfile() {
    }

    public SocialProfile(ProviderName providerName, String email, String imageUrl, Gender gender, AgeRange ageRange) {
        this.providerName = providerName;
        this.email = email;
        this.imageUrl = imageUrl;
        this.gender = gender;
        this.ageRange = ageRange;
    }

    public ProviderName getProviderName() {
        return providerName;
    }

    public String getEmail() {
        return email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Gender getGender() {
        return gender;
    }

    public AgeRange getAgeRange() {
        return ageRange;
    }
}
