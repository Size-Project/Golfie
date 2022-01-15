package com.golfie.auth.util;

import com.golfie.auth.presentation.dto.LoginUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
public class AuthArgumentResolver implements HandlerMethodArgumentResolver {
    private static final String AUTHORIZATION = "Authorization";
    private static final String CLAIM_KEY = "user";

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(LoggedInUser.class) != null
                && parameter.getParameterType().equals(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        String bearerToken = webRequest.getNativeRequest(HttpServletRequest.class).getHeader(AUTHORIZATION);
        String token = bearerToken.substring(7);

        if (!jwtTokenProvider.validateToken(token)) {
            return LoginUser.of(0L, Authority.GUEST);
        }

        Long userId = Long.parseLong(jwtTokenProvider.getPayload(token, CLAIM_KEY));

        return LoginUser.of(userId, Authority.MEMBER);
    }
}
