package com.golfie.auth.application;

import com.golfie.auth.application.dto.TokenDto;
import com.golfie.auth.exception.AlreadyRegisteredInUserException;
import com.golfie.auth.infrastructure.OauthUserInfo;
import com.golfie.auth.presentation.dto.SignUpReadyResponse;
import com.golfie.auth.presentation.dto.SignUpRequest;
import com.golfie.auth.presentation.dto.SignUpReadyRequest;
import com.golfie.auth.util.*;
import com.golfie.user.domain.User;
import com.golfie.user.domain.UserRepository;
import com.golfie.user.domain.profile.BasicProfile;
import com.golfie.user.domain.profile.ProviderName;
import com.golfie.user.domain.profile.SocialProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.golfie.common.exception.ErrorCode.ALREADY_REGISTERED_IN_USER;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final ProviderSelectorFactory providerSelectorFactory;

    public SignUpReadyResponse prepareSignUp(SignUpReadyRequest signUpReadyRequest) {
        OauthUserInfo oauthUserInfo = providerSelectorFactory
                .getUserInfoFromSocialProvider(signUpReadyRequest.getCode(), signUpReadyRequest.getProviderName());
        validateRegisterUser(oauthUserInfo);
        return oauthUserInfo.toSignUpReadyResponse();
    }

    @Transactional
    public TokenDto signUp(SignUpRequest signUpRequest) {
        User user = findOrCreateUser(signUpRequest);
        String token = jwtTokenProvider.createToken(user.getId().toString());
        return TokenDto.of(token);
    }

    private User findOrCreateUser(SignUpRequest signUpRequest) {
        Optional<User> optionalUser = userRepository.findByEmailAndProviderName(
                signUpRequest.getEmail(),
                ProviderName.valueOf(signUpRequest.getProviderName())
        );
        return optionalUser.orElseGet(() -> createUser(signUpRequest));
    }

    private User createUser(SignUpRequest signUpRequest) {
        SocialProfile socialProfile = signUpRequest.toSocialProfile();
        BasicProfile basicProfile = signUpRequest.toBasicProfile();

        User user = new User(basicProfile, socialProfile);
        return userRepository.save(user);
    }

    private void validateRegisterUser(OauthUserInfo userInfo) {
        Optional<User> user = userRepository.findByEmailAndProviderName(userInfo.getEmail(), userInfo.getProviderName());
        if (user.isPresent()) {
            throw new AlreadyRegisteredInUserException(ALREADY_REGISTERED_IN_USER);
        }
    }

}
