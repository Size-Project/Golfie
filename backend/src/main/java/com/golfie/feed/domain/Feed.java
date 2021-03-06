package com.golfie.feed.domain;

import com.golfie.feed.domain.like.Likes;
import com.golfie.user.domain.User;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.annotation.CreatedDate;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Feed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @BatchSize(size = 100)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> imageUrls;

    private String content;

    @BatchSize(size = 100)
    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Likes> likes;

    @CreatedDate
    private LocalDateTime createdAt;

    public Feed() {
        this.likes = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
    }

    public Feed(Long id, User user, List<String> imageUrls, String content) {
        this.id = id;
        this.user = user;
        this.imageUrls = imageUrls;
        this.content = content;
        this.likes = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
    }

    public Feed(User user, List<String> imageUrls, String content) {
        this.user = user;
        this.imageUrls = imageUrls;
        this.content = content;
        this.likes = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
    }

    public boolean isLikedBy(User user) {
        return likes.stream()
                .anyMatch(like -> like.isOwnedBy(user));
    }

    public void doLike(Likes like) {
        likes.add(like);
    }

    public void undoLike(User user) {
        likes.remove(findLikeByUser(user));
    }

    private Likes findLikeByUser(User user) {
        return likes.stream()
                .filter(like -> like.isOwnedBy(user))
                .findFirst()
                .get();
    }

    public int getLikeCount() {
        return likes.size();
    }

    public Long getId() {
        return id;
    }

    public User getAuthor() {
        return user;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
