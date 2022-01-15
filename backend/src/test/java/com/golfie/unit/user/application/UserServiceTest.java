package com.golfie.unit.user.application;

import com.golfie.user.application.UserService;
import com.golfie.user.domain.User;
import com.golfie.user.domain.UserRepository;
import com.golfie.user.exception.UserNotFoundException;
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
        User user = User.builder()
                .id(1L)
                .nickname("test")
                .email("test@test.com")
                .gender("male")
                .ageRange("20~29")
                .build();

        given(userRepository.findById(1L)).willReturn(Optional.of(user));

        //act
        UserProfileResponse result = userService.findUser(1L);

        UserProfileResponse userProfileResponse = UserProfileResponse.builder()
                .nickname("test")
                .email("test@test.com")
                .gender("male")
                .ageRange("20~29")
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
}
