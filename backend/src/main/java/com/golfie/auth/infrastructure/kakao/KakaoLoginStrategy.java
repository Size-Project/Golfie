package com.golfie.auth.infrastructure.kakao;

import com.golfie.auth.infrastructure.OauthUserInfo;
import com.golfie.auth.infrastructure.SocialLoginStrategy;
import com.golfie.auth.infrastructure.dto.KakaoAccessTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class KakaoLoginStrategy implements SocialLoginStrategy {
    private static final String AUTHORIZATION = "Authorization";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CHARSET = "application/x-www-form-urlencoded;charset=utf-8";
    private static final String BEARER_FORM = "Bearer %s";
    private static final String GRANT_TYPE = "authorization_code";

    private final String clientId;
    private final String redirectUri;
    private final String userInfoRequestUri;
    private final String accessTokenRequestUri;

    @Override
    public OauthUserInfo getUserInfo(String code) {
        String accessToken = getAccessToken(code);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(AUTHORIZATION, String.format(BEARER_FORM, accessToken));

        try {
            return new RestTemplate().exchange(
                    userInfoRequestUri,
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    KakaoUserInfo.class
                ).getBody();
        } catch (Exception e) {
            throw new IllegalArgumentException("");
        }
    }

    @Override
    public String getAccessToken(String code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", GRANT_TYPE);
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);

        HttpHeaders headers = new HttpHeaders();
        headers.add(CONTENT_TYPE, CHARSET);

        try {
            KakaoAccessTokenResponse kakaoAccessTokenResponse =
                    new RestTemplate().exchange(
                        accessTokenRequestUri,
                        HttpMethod.POST,
                        new HttpEntity<>(params, headers),
                        KakaoAccessTokenResponse.class
                    ).getBody();

            return kakaoAccessTokenResponse.getAccessToken();
        } catch (Exception e) {
            throw new IllegalArgumentException("");
        }
    }
}
