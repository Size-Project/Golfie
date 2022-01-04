package com.golfie.auth.presentation;

import com.golfie.auth.application.AuthService;
import com.golfie.auth.application.dto.TokenDto;
import com.golfie.auth.presentation.dto.SocialLoginRequest;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login/oauth")
    public ResponseEntity<TokenDto> socialLogin(@RequestBody SocialLoginRequest socialLoginRequest) {
        TokenDto tokenDto = authService.login(socialLoginRequest);
        return ResponseEntity.ok(tokenDto);
    }

}
