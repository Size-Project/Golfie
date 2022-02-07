package com.golfie.unit.feed.domain;

import com.golfie.common.fixture.TestUserInfo;
import com.golfie.feed.domain.Feed;
import com.golfie.feed.domain.like.Likes;
import com.golfie.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FeedTest {

    @DisplayName("피드에 등록된 좋아요의 총 개수를 반환한다.")
    @Test
    void get_Like_Count() {
        User user = new User();
        Feed feed = new Feed();

        feed.doLike(Likes.of(feed, user));
        assertThat(feed.getLikeCount()).isEqualTo(1);
    }

    @DisplayName("피드에 등록된 좋아요를 취소한다.")
    @Test
    void undo_Like() {
        User user = new User(1L, TestUserInfo.create().toSocialProfile());
        Feed feed = new Feed();

        feed.doLike(Likes.of(feed, user));
        assertThat(feed.getLikeCount()).isEqualTo(1);
        feed.undoLike(user);
        assertThat(feed.getLikeCount()).isEqualTo(0);
    }
}
