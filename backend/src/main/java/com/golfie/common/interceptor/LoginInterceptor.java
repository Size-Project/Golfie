package com.golfie.common.interceptor;

import com.golfie.auth.exception.NotAuthenticatedException;
import com.golfie.auth.presentation.dto.CurrentUser;
import com.golfie.auth.util.Authority;
import com.golfie.auth.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.golfie.common.exception.ErrorCode.NOT_AUTHENTICATED_USER;

@Slf4j
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("LoginInterceptor.preHandle");
        String token = AuthorizationExtractor.extract(request);

        if (!jwtTokenProvider.validateToken(token)) {
            throw new NotAuthenticatedException(NOT_AUTHENTICATED_USER);
        }
        return true;
    }

}
