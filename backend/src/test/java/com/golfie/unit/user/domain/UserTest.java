package com.golfie.unit.user.domain;

import com.golfie.common.fixture.TestUserInfo;
import com.golfie.feed.domain.Feed;
import com.golfie.rounding.domain.Rounding;
import com.golfie.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    private User userA;
    private User userB;

    @BeforeEach
    void setup() {
        userA = new User(TestUserInfo.create().toSocialProfile());
        userB = new User(TestUserInfo.create().toSocialProfile());
    }

    @DisplayName("피드를 등록한다.")
    @Test
    void addFeed() {
        userA.addFeed(new Feed());
        assertThat(userA.getFeedCount()).isEqualTo(1);
    }

    @DisplayName("라운딩에 참가한다.")
    @Test
    void attendRounding() {
        userA.addAttendingRound(new Rounding());
        assertThat(userA.getAttendingCount()).isEqualTo(1);
    }

    @DisplayName("라운딩에 개최한다.")
    @Test
    void hostRounding() {
        userA.addHostingRound(new Rounding());
        assertThat(userA.getJoiningCount()).isEqualTo(1);
    }

    @DisplayName("참여하고있는 모든 라운딩을 개수를 반환한다.")
    @Test
    void countJoiningRounding() {
        userA.addHostingRound(new Rounding());
        userA.addAttendingRound(new Rounding());
        userA.addAttendingRound(new Rounding());
        assertThat(userA.getJoiningCount()).isEqualTo(3);
    }

    @DisplayName("유저 A는 유저 B를 팔로우한다.")
    @Test
    void add_Following() {
        userA.addFollowing(userB);
        Set<User> followers = userB.getFollowers();
        Set<User> following = userA.getFollowing();

        assertThat(followers.size()).isEqualTo(1);
        assertThat(followers.contains(userA)).isTrue();
        assertThat(following.size()).isEqualTo(1);
        assertThat(following.contains(userB)).isTrue();
    }

    @DisplayName("유저 A는 유저 B를 언팔로우한다.")
    @Test
    void stop_Following() {
        userA.addFollowing(userB);
        userA.stopFollowing(userB);

        assertThat(userA.getFollowing().size()).isEqualTo(0);
        assertThat(userB.getFollowers().size()).isEqualTo(0);
    }

    @DisplayName("유저 A와 유저 B는 서로를 팔로우한다.")
    @Test
    void add_Following_Each_Other() {
        userA.addFollowing(userB);
        userB.addFollowing(userA);

        assertThat(userA.getFollowing().size()).isEqualTo(1);
        assertThat(userB.getFollowers().size()).isEqualTo(1);
        assertThat(userA.isFollowing(userB)).isTrue();
        assertThat(userB.isFollowing(userA)).isTrue();
    }

    @DisplayName("유저 A가 유저 B를 팔로우하고 있다면 true를 반환한다.")
    @Test
    void is_Following_True() {
        userA.addFollowing(userB);

        assertThat(userA.isFollowing(userB)).isTrue();
    }

    @DisplayName("유저 A가 유저 B를 팔로우하고 있지 않다면 false를 반환한다.")
    @Test
    void is_Following_False() {
        assertThat(userA.isFollowing(userB)).isFalse();
    }
}
