package com.golfie.unit.user.application;

import com.golfie.auth.exception.AlreadyRegisteredInUserException;
import com.golfie.user.application.UserService;
import com.golfie.user.domain.User;
import com.golfie.user.domain.UserRepository;
import com.golfie.user.domain.profile.AgeRange;
import com.golfie.user.domain.profile.Gender;
import com.golfie.user.domain.profile.ProviderName;
import com.golfie.user.domain.profile.SocialProfile;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @DisplayName("유저 프로필 정보를 반환한다.")
    @Test
    void find_User_Profile() {
        //arrange
        SocialProfile socialProfile = new SocialProfile(ProviderName.KAKAO, "test@test.com", "testImageUrl", Gender.MALE, AgeRange.TWENTY);
        User user = new User(socialProfile);

        given(userRepository.findById(1L)).willReturn(Optional.of(user));

        //act
        UserProfileResponse result = userService.findUser(1L);

        UserProfileResponse userProfileResponse = UserProfileResponse.builder()
                .imageUrl("testImageUrl")
                .email("test@test.com")
                .gender("MALE")
                .ageRange("20-29")
                .build();

        //assert
        assertThat(userProfileResponse)
                .usingRecursiveComparison()
                .isEqualTo(result);
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
    }
}
