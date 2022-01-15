package com.golfie.auth.infrastructure.naver;

import com.golfie.auth.exception.NaverApiAccessException;
import com.golfie.auth.infrastructure.OauthUserInfo;
import com.golfie.auth.infrastructure.SocialLoginStrategy;
import com.golfie.auth.infrastructure.dto.NaverAccessTokenResponse;
import com.golfie.common.exception.ErrorCode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static com.golfie.common.exception.ErrorCode.NAVER_ACCESS;

@Component
public class NaverLoginStrategy implements SocialLoginStrategy {
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER_FORM = "Bearer %s";
    private static final String GRANT_TYPE = "authorization_code";

    @Override
    public OauthUserInfo getUserInfo(String code) {
        String accessToken = getAccessToken(code);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(AUTHORIZATION, String.format(BEARER_FORM, accessToken));

        try {
            return new RestTemplate().exchange(
                    NaverOauthInfo.userInfoRequestUri,
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    NaverUserInfo.class
            ).getBody();
        } catch (Exception e) {
            throw new NaverApiAccessException(NAVER_ACCESS);
        }
    }

    @Override
    public String getAccessToken(String code) {
        String url = NaverOauthInfo.accessTokenRequestUri + "?" +
                "grant_type=" + GRANT_TYPE +
                "&client_id=" + NaverOauthInfo.clientId +
                "&client_secret=" + NaverOauthInfo.clientSecret +
                "&code=" + code;

        try {
            NaverAccessTokenResponse naverAccessTokenResponse =
                    new RestTemplate().exchange(
                            url,
                            HttpMethod.POST,
                            new HttpEntity<>(new LinkedMultiValueMap<>()),
                            NaverAccessTokenResponse.class
                    ).getBody();

            assert naverAccessTokenResponse != null;
            return naverAccessTokenResponse.getAccessToken();
        } catch (Exception e) {
            throw new NaverApiAccessException(NAVER_ACCESS);
        }
    }
}
