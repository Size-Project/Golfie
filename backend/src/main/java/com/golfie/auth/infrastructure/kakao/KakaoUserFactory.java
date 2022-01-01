package com.golfie.auth.infrastructure.kakao;

import com.golfie.auth.infrastructure.OauthUserFactory;
import com.golfie.auth.infrastructure.dto.KakaoAccessTokenResponse;
import com.golfie.auth.infrastructure.Oauth2UserInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class KakaoUserFactory implements OauthUserFactory {

    private final String clientId;
    private final String redirectUri;

    public KakaoUserFactory(@Value("${oauth.kakao.client-id}") String clientId,
                            @Value("${oauth.kakao.redirect-uri}") String redirectUri) {
        this.clientId = clientId;
        this.redirectUri = redirectUri;
    }

    @Override
    public Oauth2UserInfo getUserInfo(String code) {
        String accessToken = getAccessToken(code);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", "Bearer " + accessToken);

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<KakaoUserInfo> response =
                new RestTemplate().exchange(
                        "https://kapi.kakao.com/v2/user/me",
                        HttpMethod.GET,
                        httpEntity,
                        KakaoUserInfo.class
                );

        return response.getBody();
    }

    private String getAccessToken(String code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);

        KakaoAccessTokenResponse kakaoAccessTokenResponse =
                new RestTemplate().postForObject(
                        "https://kauth.kakao.com/oauth/token",
                        params,
                        KakaoAccessTokenResponse.class
                );

        assert kakaoAccessTokenResponse != null;
        return kakaoAccessTokenResponse.getAccessToken();
    }
}
