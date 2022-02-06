package com.golfie.acceptance.user;

import com.golfie.acceptance.AcceptanceTest;
import com.golfie.common.factory.TUserFactory;
import com.golfie.common.fixture.TUser;
import com.golfie.user.domain.User;
import com.golfie.user.presentation.dto.UserProfileResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserAcceptanceTest extends AcceptanceTest {

    private TUser other;
    private TUser member;

    @BeforeEach
    void setup() {
        TUserFactory userFactory = new TUserFactory(port);

        other = userFactory.createOtherUser().signup();
        member = userFactory.createMember().signup();
    }

    @DisplayName("로그인한 사용자가 마이프로필 정보를 조회한다.")
    @Test
    void memberUser_Reads_MyProfile() {
        UserProfileResponse userProfileResponse = member.readProfile();

        assertThat(userProfileResponse)
                .extracting("nickname")
                .isEqualTo(member.getNickname() + member.getId());

        assertThat(userProfileResponse)
                .extracting("email")
                .isEqualTo(member.getNickname() + member.getId() + "@test.com");
    }

    @DisplayName("로그인한 사용자가 다른 사용자를 팔로우한다.")
    @Test
    void memberUser_Follow_Other_User() {
        member.follow(other.getId());
    }

    @DisplayName("로그인한 사용자가 다른 사용자를 팔로우 취소한다.")
    @Test
    void memberUser_UnFollow_Other_User() {
        member.unFollow(other.getId());
    }

}
