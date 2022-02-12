package com.golfie.auth.presentation;

import com.golfie.auth.application.AuthService;
import com.golfie.auth.application.dto.TokenDto;
import com.golfie.auth.exception.SignUpRequestValidationException;
import com.golfie.auth.infrastructure.kakao.KakaoOauthInfo;
import com.golfie.auth.presentation.dto.LoginRequest;
import com.golfie.auth.presentation.dto.SignUpReadyResponse;
import com.golfie.auth.presentation.dto.SignUpRequest;
import com.golfie.auth.presentation.dto.SignUpReadyRequest;
import com.golfie.user.exception.NicknameRequestValidationException;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.golfie.common.exception.ErrorCode.ILLEGAL_NICKNAME_REQUEST;
import static com.golfie.common.exception.ErrorCode.ILLEGAL_SIGNUP_REQUEST;

@RequestMapping("/api")
@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login/oauth")
    public ResponseEntity<TokenDto> login(@RequestBody LoginRequest loginRequest) {
        KakaoOauthInfo.setRedirectUri(KakaoOauthInfo.loginRedirectUri);
        TokenDto tokenDto = authService.login(loginRequest);
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/signup/oauth/prepare")
    public ResponseEntity<SignUpReadyResponse> signUpReady(@RequestBody SignUpReadyRequest signUpReadyRequest) {
        KakaoOauthInfo.setRedirectUri(KakaoOauthInfo.signupRedirectUri);
        SignUpReadyResponse signUpReadyResponse = authService.prepareSignUp(signUpReadyRequest);
        return ResponseEntity.ok(signUpReadyResponse);
    }

    @PostMapping("/signup/oauth")
    public ResponseEntity<TokenDto> signUp(@Valid @RequestBody SignUpRequest signUpRequest,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new SignUpRequestValidationException(ILLEGAL_SIGNUP_REQUEST);
        }
        TokenDto tokenDto = authService.signUp(signUpRequest);
        return ResponseEntity.ok(tokenDto);
    }

}
