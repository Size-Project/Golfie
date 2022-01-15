package com.golfie.user.presentation;

import com.golfie.auth.presentation.dto.LoginUser;
import com.golfie.auth.util.LoggedInUser;
import com.golfie.user.application.UserService;
import com.golfie.user.presentation.dto.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/users/me")
    public ResponseEntity<UserProfileResponse> me(@LoggedInUser LoginUser loginUser) {
        UserProfileResponse userProfileResponse = userService.findUser(loginUser.getId());
        return ResponseEntity.ok(userProfileResponse);
    }
}
