package com.golfie.auth.util;

import com.golfie.auth.exception.NotAuthenticatedUserException;
import com.golfie.auth.presentation.dto.LoginUser;
import com.golfie.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

import static com.golfie.common.exception.ErrorCode.NOT_AUTHENTICATED_USER;

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
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws IllegalAccessException {
        String bearerToken = webRequest.getNativeRequest(HttpServletRequest.class).getHeader(AUTHORIZATION);
        String token = bearerToken.substring(7);

        if (!jwtTokenProvider.validateToken(token)) {
            throw new NotAuthenticatedUserException(NOT_AUTHENTICATED_USER);
        }

        Long userId = Long.parseLong(jwtTokenProvider.getPayload(token, CLAIM_KEY));

        return LoginUser.of(userId, Authority.MEMBER);
    }
}
