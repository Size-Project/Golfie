package com.golfie.rounding.presentation.dto;

import com.golfie.style.domain.Style;

public class StyleDto {

    private String ageRange;
    private String averageHit;
    private String mood;

    public StyleDto() {
    }

    public StyleDto(String ageRange, String averageHit, String mood) {
        this.ageRange = ageRange;
        this.averageHit = averageHit;
        this.mood = mood;
    }

    public static StyleDto of(Style style) {
        return new StyleDto(style.getAgeRange(), style.getAverageHit(), style.getMood());
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
