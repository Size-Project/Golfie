package com.golfie.unit.feed.domain;

import com.golfie.common.fixture.TestUserInfo;
import com.golfie.feed.domain.Feed;
import com.golfie.feed.domain.like.Likes;
import com.golfie.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LikeTest {

    @DisplayName("사용자가 해당 좋아요를 소유하면 true를 반환한다.")
    @Test
    void is_Owned_By() {
        User user = new User(1L, TestUserInfo.create().toSocialProfile());
        Feed feed = new Feed();

        Likes like = Likes.of(feed, user);

        assertThat(like.isOwnedBy(user)).isTrue();
    }
}
