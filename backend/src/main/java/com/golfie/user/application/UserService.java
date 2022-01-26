package com.golfie.user.application;

import com.golfie.user.domain.User;
import com.golfie.user.domain.UserRepository;
import com.golfie.user.exception.DuplicatedNicknameException;
import com.golfie.user.exception.UserNotFoundException;
import com.golfie.user.presentation.dto.NicknameRequest;
import com.golfie.user.presentation.dto.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.golfie.common.exception.ErrorCode.DUPLICATE_NICKNAME;
import static com.golfie.common.exception.ErrorCode.USER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserProfileResponse findUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        return UserProfileResponse.of(user);
    }

    @Transactional(readOnly = true)
    public void validateNickname(NicknameRequest nicknameRequest) {
        if (userRepository.existsByNickname(nicknameRequest.getNickname())) {
            throw new DuplicatedNicknameException(DUPLICATE_NICKNAME);
        }
    }
}
