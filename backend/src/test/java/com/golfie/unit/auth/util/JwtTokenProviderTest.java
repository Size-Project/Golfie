package com.golfie.unit.auth.util;

import com.golfie.auth.util.JwtTokenProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class JwtTokenProviderTest {

    @DisplayName("토큰을 생성하여 반환한다.")
    @Test
    void create_Token() {
        //arrange
        JwtTokenProvider jwtTokenProvider =
                new JwtTokenProvider("base64SecretKey", 100000L);

        //act
        String token = jwtTokenProvider.createToken("payload");

        //assert
        assertThat(token).isNotBlank();
    }

    @DisplayName("유효한 토큰의 payload를 반환한다.")
    @Test
    void get_Payload_From_Token() {
        //arrange
        JwtTokenProvider jwtTokenProvider =
                new JwtTokenProvider("base64SecretKey", 100000L);

        String token = jwtTokenProvider.createToken("payload");

        //act
        String payload = jwtTokenProvider.getPayload(token, "user");

        //assert
        assertThat(payload).isEqualTo("payload");
    }

    @DisplayName("유효한 토큰은 true를 반환한다.")
    @Test
    void valid_Token_Return_True() {
        //arrange
        JwtTokenProvider jwtTokenProvider =
                new JwtTokenProvider("base64SecretKey", 100000L);

        String token = jwtTokenProvider.createToken("payload");

        //act
        boolean result = jwtTokenProvider.validateToken(token);

        //assert
        assertThat(result).isTrue();
    }

    @DisplayName("유효하지 않은 토큰은 false를 반환한다.")
    @Test
    void invalid_Token_Return_False() {
        //arrange
        JwtTokenProvider jwtTokenProvider =
                new JwtTokenProvider("base64SecretKey", 100000L);

        String token = "Invalid token";

        //act
        boolean result = jwtTokenProvider.validateToken(token);

        //assert
        assertThat(result).isFalse();
    }

    @DisplayName("만료된 토큰은 false를 반환한다.")
    @Test
    void expired_Token_Return_False() {
        //arrange
        JwtTokenProvider jwtTokenProvider =
                new JwtTokenProvider("base64SecretKey", 0L);

        String token = jwtTokenProvider.createToken("payload");

        //act
        boolean result = jwtTokenProvider.validateToken(token);

        //assert
        assertThat(result).isFalse();
    }

}