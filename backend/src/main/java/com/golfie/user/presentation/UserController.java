package com.golfie.user.presentation;

import com.golfie.auth.presentation.dto.CurrentUser;
import com.golfie.auth.util.Authentication;
import com.golfie.user.application.UserService;
import com.golfie.user.exception.NicknameRequestValidationException;
import com.golfie.user.presentation.dto.NicknameRequest;
import com.golfie.user.presentation.dto.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import static com.golfie.common.exception.ErrorCode.ILLEGAL_NICKNAME_REQUEST;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/users/me")
    public ResponseEntity<UserProfileResponse> me(@Authentication CurrentUser currentUser) {
        UserProfileResponse userProfileResponse = userService.findUser(currentUser.getId());
        return ResponseEntity.ok(userProfileResponse);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserProfileResponse> findUser(@PathVariable Long id) {
        UserProfileResponse userProfileResponse = userService.findUser(id);
        return ResponseEntity.ok(userProfileResponse);
    }

    @PostMapping("/validate/nickname")
    public ResponseEntity<Void> validateNickname(@Valid @RequestBody NicknameRequest nicknameRequest,
                                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new NicknameRequestValidationException(ILLEGAL_NICKNAME_REQUEST);
        }
        userService.validateNickname(nicknameRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/users/follow")
    public ResponseEntity<Void> follow(@Authentication CurrentUser currentUser, @RequestParam("userId") Long userId) {
        userService.follow(currentUser, userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/unfollow")
    public ResponseEntity<Void> unFollow(@Authentication CurrentUser currentUser, @RequestParam("userId") Long userId) {
        userService.unFollow(currentUser, userId);
        return ResponseEntity.ok().build();
    }

}
