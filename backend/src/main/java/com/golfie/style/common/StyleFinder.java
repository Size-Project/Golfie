package com.golfie.style.common;

import com.golfie.style.domain.Style;
import com.golfie.style.domain.StyleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class StyleFinder {

    private final StyleRepository styleRepository;

    public Style findOrCreate(String hit, String ageRange, String mood) {
        Optional<Style> optionalStyle = styleRepository.findByAverageHitAndAgeRangeAndMood(hit, ageRange, mood);

        Style style;
        if (optionalStyle.isEmpty()) {
            style = styleRepository.save(Style.builder()
                    .averageHit(hit)
                    .ageRange(ageRange)
                    .mood(mood)
                    .build());
        } else {
            style = optionalStyle.get();
        }

        return style;
    }
}
