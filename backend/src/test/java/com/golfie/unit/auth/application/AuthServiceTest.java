package com.golfie.unit.auth.application;

import com.golfie.auth.application.AuthService;
import com.golfie.auth.application.ProviderSelectorFactory;
import com.golfie.auth.application.dto.TokenDto;
import com.golfie.auth.exception.AlreadyRegisteredInUserException;
import com.golfie.auth.presentation.dto.LoginRequest;
import com.golfie.auth.presentation.dto.SignUpReadyRequest;
import com.golfie.auth.presentation.dto.SignUpReadyResponse;
import com.golfie.auth.presentation.dto.SignUpRequest;
import com.golfie.auth.util.JwtTokenProvider;
import com.golfie.common.fixture.TestUserInfo;
import com.golfie.user.domain.User;
import com.golfie.user.domain.UserRepository;
import com.golfie.user.domain.profile.*;
import com.golfie.user.exception.UserNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
    private static final String CODE = "CODE";
    private static final String PROVIDER_NAME = "TEST";
    private static final String JWT_TOKEN = "Bearer token";

    @InjectMocks
    private AuthService authService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private ProviderSelectorFactory providerSelectorFactory;

    @DisplayName("로그인에 성공하면 토큰을 반환한다.")
    @Test
    void login_Success_And_Return_Token() {
        //arrange
        LoginRequest loginRequest = new LoginRequest(CODE, PROVIDER_NAME);
        TestUserInfo userInfo = TestUserInfo.create();
        User user = new User(1L, userInfo.toSocialProfile());

        given(providerSelectorFactory.getUserInfoFromSocialProvider(CODE, PROVIDER_NAME)).willReturn(userInfo);
        given(userRepository.findByEmailAndProviderName(any(), any())).willReturn(Optional.of(user));
        given(jwtTokenProvider.createToken(any())).willReturn(JWT_TOKEN);

        //act
        TokenDto tokenDto = authService.login(loginRequest);

        //assert
        assertThat(tokenDto.getAccessToken()).isEqualTo(JWT_TOKEN);

        verify(providerSelectorFactory, times(1))
                .getUserInfoFromSocialProvider(CODE, PROVIDER_NAME);
        verify(userRepository, times(1))
                .findByEmailAndProviderName(any(), any());
        verify(jwtTokenProvider, times(1))
                .createToken(any());
    }

    @DisplayName("로그인 - 존재하지 않는 회원일 경우 예외를 반환한다.")
    @Test
    void user_Not_Found_Exception() {
        //arrange
        LoginRequest loginRequest = new LoginRequest(CODE, PROVIDER_NAME);
        TestUserInfo userInfo = TestUserInfo.create();

        given(providerSelectorFactory.getUserInfoFromSocialProvider(CODE, PROVIDER_NAME)).willReturn(userInfo);
        given(userRepository.findByEmailAndProviderName(any(), any())).willReturn(Optional.empty());

        //act and assert
        assertThrows(UserNotFoundException.class, () -> {
            authService.login(loginRequest);
        });

        verify(providerSelectorFactory, times(1))
                .getUserInfoFromSocialProvider(CODE, PROVIDER_NAME);
        verify(userRepository, times(1))
                .findByEmailAndProviderName(any(), any());
    }

    @DisplayName("회원가입 준비 - 소셜 계정 정보를 가져온 후 반환한다.")
    @Test
    void return_Social_User_Profile() {
        //arrange
        SignUpReadyRequest signUpReadyRequest = new SignUpReadyRequest(CODE, PROVIDER_NAME);
        TestUserInfo userInfo = TestUserInfo.create();

        SignUpReadyResponse target = userInfo.toSignUpReadyResponse();

        given(providerSelectorFactory.getUserInfoFromSocialProvider(CODE, PROVIDER_NAME)).willReturn(userInfo);

        //act
        SignUpReadyResponse signUpReadyResponse = authService.prepareSignUp(signUpReadyRequest);

        //assert
        assertThat(signUpReadyResponse)
                .usingRecursiveComparison()
                .isEqualTo(target);

        verify(providerSelectorFactory, times(1))
                .getUserInfoFromSocialProvider(CODE, PROVIDER_NAME);
    }

    @DisplayName("회원가입 완료 - 새로운 유저를 DB에 insert하고 토큰을 반환한다.")
    @Test
    void save_User_And_Return_JwtToken() {
        //arrange
        SignUpRequest signUpRequest = new SignUpRequest(
                "test@test.com",
                "testImageUrl",
                "20-29",
                "MALE",
                "TEST",
                "junslee",
                "hello"
        );

        SocialProfile socialProfile = signUpRequest.toSocialProfile();
        BasicProfile basicProfile = signUpRequest.toBasicProfile();

        User user = new User(1L, basicProfile, socialProfile);

        given(userRepository.findByEmailAndProviderName(
                signUpRequest.getEmail(),
                ProviderName.valueOf(signUpRequest.getProviderName()))
        ).willReturn(Optional.empty());
        given(userRepository.save(any())).willReturn(user);
        given(jwtTokenProvider.createToken(user.getId().toString())).willReturn(JWT_TOKEN);

        //act
        TokenDto tokenDto = authService.signUp(signUpRequest);

        //assert
        assertThat(tokenDto.getAccessToken()).isEqualTo(JWT_TOKEN);

        verify(jwtTokenProvider, times(1))
                .createToken(user.getId().toString());
        verify(userRepository, times(1))
                .findByEmailAndProviderName(signUpRequest.getEmail(), ProviderName.valueOf(signUpRequest.getProviderName()));
        verify(userRepository, times(1))
                .save(any());
    }

    @DisplayName("이미 가입된 회원의 경우 예외를 반환하다.")
    @Test
    void user_Already_Registered_In_Exception() {
        //arrange
        SignUpReadyRequest signUpReadyRequest = new SignUpReadyRequest(CODE, PROVIDER_NAME);
        TestUserInfo userInfo = TestUserInfo.create();
        User user = new User();

        given(userRepository.findByEmailAndProviderName(any(), any())).willReturn(Optional.of(user));
        given(providerSelectorFactory.getUserInfoFromSocialProvider(CODE, PROVIDER_NAME)).willReturn(userInfo);

        //act and assert
        assertThrows(AlreadyRegisteredInUserException.class, () ->
                authService.prepareSignUp(signUpReadyRequest)
        );

        verify(userRepository, times(1))
                .findByEmailAndProviderName(any(), any());
        verify(providerSelectorFactory, times(1))
                .getUserInfoFromSocialProvider(CODE, PROVIDER_NAME);
        verify(userRepository, never())
                .save(any());
    }

}