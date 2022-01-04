package com.golfie.auth.util;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {

    private final String base64SecretKey;
    private final Long tokenValidityInMilliseconds;

    public JwtTokenProvider(
            @Value("${jwt.base64-secret}") String base64SecretKey,
            @Value("${jwt.token-validity-in-seconds}") long tokenValidityInSeconds) {
        this.base64SecretKey = base64SecretKey;
        this.tokenValidityInMilliseconds = tokenValidityInSeconds * 1000;
    }

    public String createToken(String payload) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + tokenValidityInMilliseconds);

        return Jwts.builder()
                .claim("user", payload)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS512, base64SecretKey)
                .compact();
    }

    public String getPayload(String token) {
        return Jwts.parser()
                .setSigningKey(base64SecretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(base64SecretKey).parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("유효하지 않은 토큰입니다.");
            log.trace(e.getMessage());
        } catch (ExpiredJwtException e) {
            log.info("만료된 토큰입니다.");
            log.trace(e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 형식의 토큰입니다.");
            log.trace(e.getMessage());
        }
        return false;
    }

}
