package com.golfie.user.domain;

import com.golfie.feed.domain.Feed;
import com.golfie.rounding.domain.Rounding;
import com.golfie.style.domain.Style;
import com.golfie.user.domain.profile.*;
import javax.persistence.*;
import java.util.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private SocialProfile socialProfile;

    @Embedded
    private BasicProfile basicProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    private Style style;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
    private final List<Feed> feeds;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "USER_HOSTING_ROUNDS")
    private final Set<Rounding> hostingRounds;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ATTENDING_ROUNDS")
    private final Set<Rounding> attendingRounds;

    @ManyToMany(mappedBy = "followers")
    private final Set<User> following;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "USER_RELATIONS")
    @JoinColumn(name = "FOLLOWED_ID")
    private final Set<User> followers;

    public User() {
        this.feeds = new ArrayList<>();
        this.following = new HashSet<>();
        this.followers = new HashSet<>();
        this.hostingRounds = new HashSet<>();
        this.attendingRounds = new HashSet<>();
    }

    public User(Long id, BasicProfile basicProfile, SocialProfile socialProfile, Style style) {
        this.id = id;
        this.basicProfile = basicProfile;
        this.socialProfile = socialProfile;
        this.style = style;
        this.feeds = new ArrayList<>();
        this.following = new HashSet<>();
        this.followers = new HashSet<>();
        this.hostingRounds = new HashSet<>();
        this.attendingRounds = new HashSet<>();
    }

    public User(Long id, BasicProfile basicProfile, SocialProfile socialProfile) {
        this(id, basicProfile, socialProfile, null);
    }

    public User(BasicProfile basicProfile, SocialProfile socialProfile, Style style) {
        this(null, basicProfile, socialProfile, style);
    }

    public User(BasicProfile basicProfile, SocialProfile socialProfile) {
        this(null, basicProfile, socialProfile, null);
    }

    public User(Long id, SocialProfile socialProfile) {
        this(id, new BasicProfile(), socialProfile, null);
    }

    public User(SocialProfile socialProfile) {
        this(null, new BasicProfile(), socialProfile, null);
    }

    public void addFeed(Feed feed) {
        feeds.add(feed);
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

    public int getFollowingCount() {
        return following.size();
    }

    public int getFollowerCount() {
        return followers.size();
    }

    public int getFeedCount() {
        return feeds.size();
    }

    public int getJoiningCount() {
        return attendingRounds.size() + hostingRounds.size();
    }

    public int getAttendingCount() {
        return attendingRounds.size();
    }

    public void addHostingRound(Rounding rounding) {
        hostingRounds.add(rounding);
    }

    public void addAttendingRound(Rounding rounding) {
        attendingRounds.add(rounding);
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

    public String getJob() {
        return basicProfile.getJob();
    }

    public int getAverageHit() {
        return basicProfile.getAverageHit();
    }

    public List<Feed> getFeeds() {
        return feeds;
    }

    public Set<User> getFollowing() {
        return following;
    }

    public Set<User> getFollowers() {
        return followers;
    }

    public Style getStyle() {
        return style;
    }

    public String toPayload() {
        return this.id.toString();
    }
}
