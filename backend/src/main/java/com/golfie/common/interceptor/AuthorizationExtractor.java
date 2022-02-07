package com.golfie.common.interceptor;

import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;

public class AuthorizationExtractor {

    public static String BEARER_TYPE = "Bearer";

    public static String extract(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (bearerToken.startsWith(BEARER_TYPE)) {
            return bearerToken.substring(BEARER_TYPE.length()).trim();
        }
        return null;
    }
}
