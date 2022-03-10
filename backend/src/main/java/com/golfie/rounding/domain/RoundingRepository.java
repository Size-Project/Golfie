package com.golfie.rounding.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoundingRepository extends JpaRepository<Rounding, Long> {

//    @Query("select distinct r from Rounding r join fetch r.attendee where r.attendee = :userId")
//    List<Rounding> findAllByAttendee(@Param("userId") Long id);
}
