package com.golfie.acceptance.feed;

import com.golfie.acceptance.AcceptanceTest;
import com.golfie.auth.application.dto.TokenDto;
import com.golfie.auth.presentation.dto.SignUpRequest;
import com.golfie.common.factory.TUserFactory;
import com.golfie.common.fixture.TUser;
import com.golfie.feed.domain.Feed;
import com.golfie.feed.presentation.dto.FeedResponse;
import com.golfie.user.presentation.dto.UserProfileResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
public class FeedAcceptanceTest extends AcceptanceTest {

    private TUser other1;
    private TUser other2;
    private TUser member;
    private TUser guest;

    @BeforeEach
    void setup() {
        TUserFactory userFactory = new TUserFactory(port);

        other1 = userFactory.createOtherUser().signup();
        other2 = userFactory.createOtherUser().signup();
        member = userFactory.createMember().signup();
        guest = userFactory.createGuest();

        other1.createFeed();
        other2.createFeed();
    }

    @DisplayName("로그인한 사용자가 피드를 등록한다.")
    @Test
    void memberUser_Saves_A_New_Feed()  {
        member.createFeed();
    }

    @DisplayName("로그인한 사용자가 다른 사용자들이 등록한 모든 피드를 조회한다.")
    @Test
    void memberUser_Reads_All_Feeds()  {
        List<FeedResponse> feedResponses = member.readAllFeeds();

        assertThat(feedResponses)
                .hasSize(2)
                .extracting("following")
                .containsExactly(false, false);
    }

    @DisplayName("로그인한 사용자가 다른 사용자들이 등록한 모든 피드를 조회한다. - 팔로잉 여부 true")
    @Test
    void memberUser_Reads_All_Following_User_Feeds()  {
        member.follow(other1.getId());
        member.follow(other2.getId());
        List<FeedResponse> feedResponses = member.readAllFeeds();

        assertThat(feedResponses)
                .hasSize(2)
                .extracting("following")
                .containsExactly(true, true);
    }

    @DisplayName("로그인하지 않은 사용자가 모든 피드를 조회한다.")
    @Test
    void guestUser_Reads_All_Feeds()  {
        List<FeedResponse> feedResponses = guest.readAllFeeds();

        assertThat(feedResponses)
                .hasSize(2)
                .extracting("following")
                .containsExactly(false, false);
    }

}
