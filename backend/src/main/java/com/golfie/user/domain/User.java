package com.golfie.user.domain;

import com.golfie.feed.domain.Feed;
import com.golfie.user.domain.profile.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private SocialProfile socialProfile;

    @Embedded
    private BasicProfile basicProfile;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Feed> feeds;

    @ManyToMany(mappedBy = "followers")
    private Set<User> following;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "USER_RELATIONS")
    @JoinColumn(name = "FOLLOWED_ID")
    private Set<User> followers;

    public User() {
        this.feeds = new ArrayList<>();
        this.following = new HashSet<>();
        this.followers = new HashSet<>();
    }

    public User(Long id, BasicProfile basicProfile, SocialProfile socialProfile) {
        this.id = id;
        this.basicProfile = basicProfile;
        this.socialProfile = socialProfile;
        this.feeds = new ArrayList<>();
        this.following = new HashSet<>();
        this.followers = new HashSet<>();
    }

    public User(SocialProfile socialProfile) {
        this(null, new BasicProfile(), socialProfile);
    }

    public User(BasicProfile basicProfile, SocialProfile socialProfile) {
        this(null, basicProfile, socialProfile);
    }

    public User(Long id, SocialProfile socialProfile) {
        this(id, new BasicProfile(), socialProfile);
    }

    public void addFollowing(User followed) {
        followed.addFollower(this);
    }

    private void addFollower(User follower) {
        followers.add(follower);
        follower.following.add(this);
    }

    public void stopFollowing(User followed) {
        following.remove(followed);
        Set<User> followers = followed.followers;
        followers.remove(this);
    }

    public boolean isFollowing(User otherUser) {
        return following.contains(otherUser);
    }

    public Long getId() {
        return id;
    }

    public ProviderName getProviderName() {
        return socialProfile.getProviderName();
    }

    public String getEmail() {
        return socialProfile.getEmail();
    }

    public String getImageUrl() {
        return socialProfile.getImageUrl();
    }

    public AgeRange getAgeRange() {
        return socialProfile.getAgeRange();
    }

    public Gender getGender() {
        return socialProfile.getGender();
    }

    public String getNickname() {
        return basicProfile.getNickname();
    }

    public String getBio() {
        return basicProfile.getBio();
    }

    public Set<User> getFollowing() {
        return following;
    }

    public Set<User> getFollowers() {
        return followers;
    }

    public String toPayload() {
        return this.id.toString();
    }
}
