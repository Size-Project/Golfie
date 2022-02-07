package com.golfie.unit.user.application;

import com.golfie.auth.presentation.dto.CurrentUser;
import com.golfie.auth.util.Authority;
import com.golfie.common.fixture.TestUserInfo;
import com.golfie.user.application.UserService;
import com.golfie.user.domain.User;
import com.golfie.user.domain.UserRepository;
import com.golfie.user.exception.DuplicatedNicknameException;
import com.golfie.user.exception.UserNotFoundException;
import com.golfie.user.presentation.dto.NicknameRequest;
import com.golfie.user.presentation.dto.UserProfileResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @DisplayName("사용자 프로필 정보를 반환한다.")
    @Test
    void find_User_Profile() {
        //arrange
        User user = new User(1L, TestUserInfo.create().toSocialProfile());
        given(userRepository.findById(1L)).willReturn(Optional.of(user));

        //act
        UserProfileResponse result = userService.findUser(1L);

        UserProfileResponse userProfileResponse = UserProfileResponse.builder()
                .id("1")
                .imageUrl("profileImageUrl")
                .email("test@test.com")
                .gender("MALE")
                .ageRange("20-29")
                .build();

        //assert
        assertThat(userProfileResponse)
                .usingRecursiveComparison()
                .isEqualTo(result);

        verify(userRepository, times(1))
                .findById(1L);
    }

    @DisplayName("사용자가 다른 사용자를 팔로우한다.")
    @Test
    void follow() {
        //arrange
        User userA = new User(1L, TestUserInfo.create().toSocialProfile());
        User userB = new User(2L, TestUserInfo.create().toSocialProfile());
        given(userRepository.findById(1L)).willReturn(Optional.of(userA));
        given(userRepository.findById(2L)).willReturn(Optional.of(userB));

        //act
        userService.follow(CurrentUser.of(1L, Authority.MEMBER), 2L);

        //assert
        assertThat(userA.getFollowing()).contains(userB);
        assertThat(userA.isFollowing(userB)).isTrue();

        verify(userRepository, times(1))
                .findById(1L);
        verify(userRepository, times(1))
                .findById(2L);
    }

    @DisplayName("사용자가 다른 사용자를 팔로우 취소한다.")
    @Test
    void unFollow() {
        //arrange
        User userA = new User(1L, TestUserInfo.create().toSocialProfile());
        User userB = new User(2L, TestUserInfo.create().toSocialProfile());
        userA.addFollowing(userB);

        given(userRepository.findById(1L)).willReturn(Optional.of(userA));
        given(userRepository.findById(2L)).willReturn(Optional.of(userB));

        //act
        userService.unFollow(CurrentUser.of(1L, Authority.MEMBER), 2L);

        //assert
        assertThat(userA.getFollowing()).isEmpty();
        assertThat(userA.isFollowing(userB)).isFalse();

        verify(userRepository, times(1))
                .findById(1L);
        verify(userRepository, times(1))
                .findById(2L);
    }

    @DisplayName("존재하지 않는 회원의 경우 예외를 반환하다.")
    @Test
    void user_Not_Found_Exception() {
        //act and assert
        assertThrows(UserNotFoundException.class, () ->
            userService.findUser(1L)
        );
    }

    @DisplayName("중복된 닉네임은 예외를 반환한다.")
    @Test
    void duplicate_User_Nickname_Exception() {
        //arrange
        NicknameRequest nicknameRequest = new NicknameRequest("junslee");
        given(userRepository.existsByNickname("junslee")).willReturn(true);

        //act and assert
        assertThrows(DuplicatedNicknameException.class, () ->
            userService.validateNickname(nicknameRequest)
        );

        verify(userRepository, times(1))
                .existsByNickname("junslee");
    }

    @DisplayName("중복되지 않은 닉네임의 경우 예외를 반환하지 않는다.")
    @Test
    void valid_Nickname() {
        //arrange
        NicknameRequest nicknameRequest = new NicknameRequest("junslee");
        given(userRepository.existsByNickname("junslee")).willReturn(false);

        //act and assert
        assertDoesNotThrow(() ->
                userService.validateNickname(nicknameRequest)
        );

        verify(userRepository, times(1))
                .existsByNickname("junslee");
    }
}
