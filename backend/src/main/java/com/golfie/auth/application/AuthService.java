package com.golfie.auth.application;

import com.golfie.auth.application.dto.TokenDto;
import com.golfie.auth.infrastructure.Oauth2UserInfo;
import com.golfie.auth.presentation.dto.SocialLoginRequest;
import com.golfie.auth.util.*;
import com.golfie.user.domain.User;
import com.golfie.user.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final SocialClient socialClient;

    public AuthService(UserRepository userRepository, JwtTokenProvider jwtTokenProvider, SocialClient socialClient) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.socialClient = socialClient;
    }

    @Transactional
    public TokenDto login(SocialLoginRequest socialLoginRequest) {
        Oauth2UserInfo userInfo = socialClient
                .getUserInfo(socialLoginRequest.getCode(), socialLoginRequest.getProvider());

        User user = findOrCreateUser(userInfo);

        String token = jwtTokenProvider.createToken(user.getId().toString());
        return TokenDto.of(token);
    }

    private User findOrCreateUser(Oauth2UserInfo userInfo) {
        Optional<User> optionalUser = userRepository.findByEmail(userInfo.getEmail());

        User user;

        if (optionalUser.isPresent()) {
            user = optionalUser.get();
            user.update(user.getProviderId());
        } else {
            user = createUser(userInfo);
        }

        return user;
    }

    private User createUser(Oauth2UserInfo userInfo) {
        User user = User.builder()
                .providerId(userInfo.getProviderId())
                .email(userInfo.getEmail())
                .build();

        return userRepository.save(user);
    }
}
