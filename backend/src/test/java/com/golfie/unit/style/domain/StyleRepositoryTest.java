package com.golfie.unit.style.domain;

import com.golfie.style.domain.Style;
import com.golfie.style.domain.StyleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class StyleRepositoryTest {

    @Autowired
    private StyleRepository styleRepository;

    @DisplayName("타수, 연령대, 분위기로 해당하는 style을 조회한다.")
    @Test
    void findByAverageHitAndAgeRangeAndMood() {
        styleRepository.save(Style.builder()
                .averageHit("100-120")
                .ageRange("20-29")
                .mood("분위기")
                .build());

        Optional<Style> style = styleRepository
                .findByAverageHitAndAgeRangeAndMood("100-120", "20-29", "분위기");

        assertThat(style.get().getAverageHit()).isEqualTo("100-120");
        assertThat(style.get().getAgeRange()).isEqualTo("20-29");
        assertThat(style.get().getMood()).isEqualTo("분위기");
    }
}
