package com.golfie.user.application;

import com.golfie.user.domain.User;
import com.golfie.user.domain.UserRepository;
import com.golfie.user.exception.UserNotFoundException;
import com.golfie.user.presentation.dto.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.golfie.common.exception.ErrorCode.USER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserProfileResponse findUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));

        return UserProfileResponse.of(user);
    }
}
