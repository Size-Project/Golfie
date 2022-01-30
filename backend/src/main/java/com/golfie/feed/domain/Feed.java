package com.golfie.feed.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Feed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @ElementCollection
    private List<String> imageUrls;

    private String content;

    public Feed() {
    }

    public Feed(Long userId, List<String> imageUrls, String content) {
        this.userId = userId;
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
