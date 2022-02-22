package com.golfie.feed.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FeedRepository extends JpaRepository<Feed, Long> {

    @Query("select f from Feed f " +
            "join fetch f.imageUrls " +
            "join fetch f.user " +
            "order by f.createdAt desc")
    Slice<Feed> findAllFeeds(Pageable pageable);
}
