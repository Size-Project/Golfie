package com.golfie.feed.domain;

import com.golfie.common.domain.BaseTimeEntity;
import com.golfie.feed.domain.like.Likes;
import com.golfie.user.domain.User;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "feed_id")
    private List<Likes> likes;

    public Feed() {
        this.likes = new ArrayList<>();
    }

    public Feed(User user, List<String> imageUrls, String content) {
        this.user = user;
        this.imageUrls = imageUrls;
        this.content = content;
        this.likes = new ArrayList<>();
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

}
