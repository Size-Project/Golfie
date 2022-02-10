package com.golfie.feed.domain.like;

import com.golfie.feed.domain.Feed;
import com.golfie.user.domain.User;
import lombok.Builder;

import javax.persistence.*;

@Entity
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id")
    private Feed feed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Likes() {
    }

    @Builder
    public Likes(Feed feed, User user) {
        this.feed = feed;
        this.user = user;
    }

    public static Likes of(Feed feed, User user) {
        return Likes.builder()
                .feed(feed)
                .user(user)
                .build();
    }

    public boolean isOwnedBy(User user) {
        return this.user.getId().equals(user.getId());
    }

}
