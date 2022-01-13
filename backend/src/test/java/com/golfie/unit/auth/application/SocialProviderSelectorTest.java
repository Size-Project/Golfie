package com.golfie.unit.auth.application;

import com.golfie.auth.application.SocialProviderSelector;
import com.golfie.auth.exception.UnsupportedSocialProviderException;
import com.golfie.auth.infrastructure.SocialProvider;
import com.golfie.common.fixture.TestUserInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class SocialProviderSelectorTest {

    @Mock
    private SocialProviderSelector socialProviderSelector;

    @DisplayName("카카오 소셜 서비스를 이용할 수 있다.")
    @Test
    void select_Kakao_Social_Service() {
        //arrange
        String providerName = "KAKAO";

        //act
        SocialProvider socialProvider =
                ReflectionTestUtils.invokeMethod(socialProviderSelector, "getSocialProviderByName", providerName);

        //assert
        assertThat(socialProvider.getProviderName()).isEqualTo(providerName);
    }

    @DisplayName("네이버 소셜 서비스를 이용할 수 있다.")
    @Test
    void select_Naver_Social_Service() {
        //arrange
        String providerName = "NAVER";

        //act
        SocialProvider socialProvider =
                ReflectionTestUtils.invokeMethod(socialProviderSelector, "getSocialProviderByName", providerName);

        //assert
        assertThat(socialProvider.getProviderName()).isEqualTo(providerName);
    }

    @DisplayName("지원하지 않는 소셜 서비스 요청 시 예외 처리를 한다.")
    @Test
    void unsupported_Social_Service_Exception() {
        //arrange
        String providerName = "UNSUPPORTED_PROVIDER";

        //act and assert
        assertThrows(UnsupportedSocialProviderException.class, () -> {
            ReflectionTestUtils.invokeMethod(socialProviderSelector, "getSocialProviderByName", providerName);
        });
    }
}