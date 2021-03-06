package com.golfie.acceptance.rounding;

import com.golfie.acceptance.AcceptanceTest;
import com.golfie.common.factory.TUserFactory;
import com.golfie.common.fixture.TUser;
import com.golfie.rounding.domain.course.Course;
import com.golfie.rounding.presentation.dto.RoundingResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RoundingAcceptanceTest extends AcceptanceTest {

    private TUser member;
    private TUser other;
    private TUser guest;

    @BeforeEach
    void setup() {
        TUserFactory userFactory = new TUserFactory(port);

        member = userFactory.createMember().signup();
        other = userFactory.createOtherUser().signup();
        guest = userFactory.createGuest();

        courseRepository.save(new Course("courseName", "courseAddress"));
    }

    @DisplayName("로그인된 사용자가 새로운 라운딩을 등록한다.")
    @Test
    void memberUser_Registers_A_New_Rounding() {
        member.createRounding();
    }

    @DisplayName("게스트 사용자가 모든 라운딩을 조회한다.")
    @Test
    void read_All_Roundings() {
        other.createRounding();

        List<RoundingResponse> roundingResponses = guest.readAllRoundings();

        assertThat(roundingResponses)
                .hasSize(1)
                .extracting("title")
                .containsExactly("title");

        assertThat(roundingResponses)
                .extracting("course.name")
                .containsExactly("courseName");
    }

    @DisplayName("로그인된 사용자가 라운딩에 조인한다.")
    @Test
    void join_Rounding() {
        other.createRounding();
        member.joinRounding();
    }

    @DisplayName("로그인된 사용자가 자신이 조인한 모든 라운딩을 조회한다.")
    @Test
    void join_All_My_Roundings() {
        other.createRounding();
        member.joinRounding();

        List<RoundingResponse> roundingResponses = member.readAllMyRoundings();

        assertThat(roundingResponses)
                .hasSize(1)
                .extracting("title")
                .containsExactly("title");

        assertThat(roundingResponses)
                .extracting("course.name")
                .containsExactly("courseName");
    }

    @DisplayName("게스트 사용자가 단일 라운딩을 조회한다.")
    @Test
    void read_One_Rounding() {
        other.createRounding();

        RoundingResponse roundingResponse = guest.readRounding();

        assertThat(roundingResponse)
                .extracting("title")
                .isEqualTo("title");
    }
}
