package com.golfie.style.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StyleRepository extends JpaRepository<Style, Long> {

//    @Query("SELECT s FROM Style s WHERE s.averageHit = :hit AND s.ageRange = :age AND s.mood = :mood")
//    Optional<Style> findByTags(@Param("hit") String preferredHit,
//                               @Param("age") String preferredAge,
//                               @Param("mood") String preferredMood);

    Optional<Style> findByAverageHitAndAgeRangeAndMood(String preferredHit, String preferredAge, String preferredMood);
}
