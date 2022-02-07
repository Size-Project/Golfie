package com.golfie.unit.common;

import com.golfie.common.interceptor.AuthorizationExtractor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthorizationExtractorTest {

    @DisplayName("Authorization 헤더 값이 Bearer 타입인 경우 파싱하여 토큰 문자열을 반환한다.")
    @Test
    void extractor_Return_Parsed_Token() {
        String bearerToken = "Bearer Token";
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader(HttpHeaders.AUTHORIZATION, bearerToken);

        String extractedToken = AuthorizationExtractor.extract(request);
        assertThat(extractedToken).isEqualTo(bearerToken.substring(7));
    }

    @DisplayName("Authorization 헤더 값이 Bearer 타입이 아닐 경우 null을 반환한다.")
    @Test
    void extractor_Return_Null() {
        String bearerToken = "Token";
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader(HttpHeaders.AUTHORIZATION, bearerToken);

        String extractedToken = AuthorizationExtractor.extract(request);
        assertThat(extractedToken).isNull();
    }
}
