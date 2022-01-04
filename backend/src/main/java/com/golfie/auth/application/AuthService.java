package com.golfie.auth.application;

import com.golfie.auth.application.dto.TokenDto;
import com.golfie.auth.infrastructure.OauthUserInfo;
import com.golfie.auth.presentation.dto.SocialLoginRequest;
import com.golfie.auth.util.*;
import com.golfie.user.domain.User;
import com.golfie.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final ProviderSelectorFactory providerSelectorFactory;

    @Transactional
    public TokenDto login(SocialLoginRequest socialLoginRequest) {
        OauthUserInfo oauthUserInfo = providerSelectorFactory
                .getUserInfoFromSocialProvider(socialLoginRequest.getCode(), socialLoginRequest.getProviderName());

        User user = findOrCreateUser(oauthUserInfo);

        String token = jwtTokenProvider.createToken(user.getId().toString());
        return TokenDto.of(token);
    }

    private User findOrCreateUser(OauthUserInfo userInfo) {
        Optional<User> optionalUser = userRepository
                .findByEmailAndProviderId(userInfo.getEmail(), userInfo.getProviderId());

        return optionalUser.orElseGet(() -> createUser(userInfo));
    }

    private User createUser(OauthUserInfo userInfo) {
        User user = User.builder()
                .providerId(userInfo.getProviderId())
                .email(userInfo.getEmail())
                .ageRange(userInfo.getAgeRange())
                .gender(userInfo.getGender())
                .build();

        return userRepository.save(user);
    }
}
