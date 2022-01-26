package com.golfie.auth.presentation;

import com.golfie.auth.application.AuthService;
import com.golfie.auth.application.dto.TokenDto;
import com.golfie.auth.presentation.dto.SignUpReadyResponse;
import com.golfie.auth.presentation.dto.SignUpRequest;
import com.golfie.auth.presentation.dto.SignUpReadyRequest;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup/oauth/prepare")
    public ResponseEntity<SignUpReadyResponse> signUpReady(@RequestBody SignUpReadyRequest signUpReadyRequest) {
        SignUpReadyResponse signUpReadyResponse = authService.prepareSignUp(signUpReadyRequest);
        return ResponseEntity.ok(signUpReadyResponse);
    }

    @PostMapping("/signup/oauth")
    public ResponseEntity<TokenDto> signUp(@RequestBody SignUpRequest signUpRequest) {
        TokenDto tokenDto = authService.signUp(signUpRequest);
        return ResponseEntity.ok(tokenDto);
    }

}
