package com.golfie.unit.auth.application;

import com.golfie.auth.application.AuthService;
import com.golfie.auth.application.ProviderSelectorFactory;
import com.golfie.auth.application.dto.TokenDto;
import com.golfie.auth.presentation.dto.SocialLoginRequest;
import com.golfie.auth.util.JwtTokenProvider;
import com.golfie.common.fixture.TestUserInfo;
import com.golfie.user.domain.User;
import com.golfie.user.domain.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
    private static final String CODE = "CODE";
    private static final String PROVIDER_NAME = "TEST";
    private static final String JWT_TOKEN = "Jwt Token";

    @InjectMocks
    private AuthService authService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private ProviderSelectorFactory providerSelectorFactory;

    @DisplayName("소셜 로그인(첫 로그인) - 소셜 계정 개인 정보를 가져와서 DB에 insert한다.")
    @Test
    void socialLogin_Save_User_Profile_And_Return_JwtToken() {
        //arrange
        SocialLoginRequest socialLoginRequest = new SocialLoginRequest(CODE, PROVIDER_NAME);

        TestUserInfo userInfo = TestUserInfo.create();

        User user = User.builder()
                .id(1L)
                .providerId("12345678")
                .email("test@test.com")
                .gender("male")
                .ageRange("20~29")
                .build();

        given(providerSelectorFactory.getUserInfoFromSocialProvider(CODE, PROVIDER_NAME)).willReturn(userInfo);
        given(jwtTokenProvider.createToken(user.getId().toString())).willReturn(JWT_TOKEN);
        given(userRepository.findByEmailAndProviderId(userInfo.getEmail(), userInfo.getProviderId())).willReturn(Optional.empty());
        given(userRepository.save(any())).willReturn(user);

        //act
        TokenDto tokenResponse = authService.login(socialLoginRequest);

        //assert
        assertThat(tokenResponse.getAccessToken()).isEqualTo(JWT_TOKEN);

        verify(providerSelectorFactory, times(1))
                .getUserInfoFromSocialProvider(CODE, PROVIDER_NAME);
        verify(jwtTokenProvider, times(1))
                .createToken(user.getId().toString());
        verify(userRepository, times(1))
                .findByEmailAndProviderId(userInfo.getEmail(), userInfo.getProviderId());
        verify(userRepository, times(1))
                .save(any());
    }

    @DisplayName("소셜 로그인(재 로그인) - DB에서 이미 저장되어 있는 유저 정보를 가져온다.")
    @Test
    void socialLogin_Find_Exists_User_And_Return_JwtToken() {
        //arrange
        SocialLoginRequest socialLoginRequest = new SocialLoginRequest(CODE, PROVIDER_NAME);

        TestUserInfo userInfo = TestUserInfo.create();

        User user = User.builder()
                .id(1L)
                .providerId("12345678")
                .email("test@test.com")
                .gender("male")
                .ageRange("20~29")
                .build();

        given(providerSelectorFactory.getUserInfoFromSocialProvider(CODE, PROVIDER_NAME)).willReturn(userInfo);
        given(jwtTokenProvider.createToken(user.getId().toString())).willReturn(JWT_TOKEN);
        given(userRepository.findByEmailAndProviderId(userInfo.getEmail(), userInfo.getProviderId())).willReturn(Optional.of(user));

        //act
        TokenDto tokenResponse = authService.login(socialLoginRequest);

        //assert
        assertThat(tokenResponse.getAccessToken()).isEqualTo(JWT_TOKEN);

        verify(providerSelectorFactory, times(1))
                .getUserInfoFromSocialProvider(CODE, PROVIDER_NAME);
        verify(jwtTokenProvider, times(1))
                .createToken(user.getId().toString());
        verify(userRepository, times(1))
                .findByEmailAndProviderId(userInfo.getEmail(), userInfo.getProviderId());
        verify(userRepository, never())
                .save(any());
    }
}