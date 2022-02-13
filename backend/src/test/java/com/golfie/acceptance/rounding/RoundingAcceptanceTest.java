package com.golfie.acceptance.rounding;

import com.golfie.acceptance.AcceptanceTest;
import com.golfie.common.factory.TUserFactory;
import com.golfie.common.fixture.TUser;
import com.golfie.rounding.domain.course.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RoundingAcceptanceTest extends AcceptanceTest {

    private TUser member;
    private TUser guest;

    @BeforeEach
    void setup() {
        TUserFactory userFactory = new TUserFactory(port);

        member = userFactory.createMember().signup();
        guest = userFactory.createGuest();

        courseRepository.save(new Course("courseName", "courseAddress"));
    }

    @DisplayName("로그인된 사용자가 새로운 라운딩을 등록한다.")
    @Test
    void memberUser_Registers_A_New_Rounding() {
        member.createRounding();
    }
}
