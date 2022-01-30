package com.golfie.feed.domain;

import com.golfie.common.domain.BaseTimeEntity;
import com.golfie.user.domain.User;

import javax.persistence.*;
import java.util.List;

@Entity
public class Feed extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ElementCollection
    private List<String> imageUrls;

    private String content;

    public Feed() {
    }

    public Feed(User user, List<String> imageUrls, String content) {
        this.user = user;
        this.imageUrls = imageUrls;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
