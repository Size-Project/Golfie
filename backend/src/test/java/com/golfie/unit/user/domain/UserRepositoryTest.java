package com.golfie.unit.user.domain;

import com.golfie.common.fixture.TestUserInfo;
import com.golfie.user.domain.User;
import com.golfie.user.domain.UserRepository;
import com.golfie.user.domain.profile.BasicProfile;
import com.golfie.user.domain.profile.SocialProfile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @DisplayName("이메일과 소셜 provider 이름으로 유저를 조회한다.")
    @Test
    void findByEmailAndProviderName() {
        User saveUser = new User(TestUserInfo.create().toSocialProfile());
        userRepository.save(saveUser);

        User findUser = userRepository.findByEmailAndProviderName(saveUser.getEmail(), saveUser.getProviderName()).get();

        assertThat(findUser)
                .usingRecursiveComparison()
                .isEqualTo(saveUser);
    }

    @DisplayName("닉네임으로 존재 여부를 확인한다.")
    @Test
    void existsByNickname() {
        SocialProfile socialProfile = TestUserInfo.create().toSocialProfile();
        BasicProfile basicProfile = new BasicProfile("junslee");
        User saveUser = new User(basicProfile, socialProfile);
        userRepository.save(saveUser);

        boolean isExists = userRepository.existsByNickname(saveUser.getNickname());

        assertThat(isExists).isTrue();
    }
}
