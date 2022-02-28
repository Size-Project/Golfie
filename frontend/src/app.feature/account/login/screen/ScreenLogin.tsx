import React, { useEffect } from 'react';
import { useRouter } from 'next/router';
import styled from 'styled-components';
import Link from 'next/link';
import API from 'app.modules/api';
import { setCookie } from 'nookies';

const ScreenLogin = () => {
  const router = useRouter();
  const kakaoLink = `https://kauth.kakao.com/oauth/authorize?client_id=${process.env.FIELD_TRIP_KAKAO_CLIENT_ID}&redirect_uri=${process.env.FIELD_TRIP_KAKAO_REDIRECT_URI_LOGIN}&response_type=${process.env.FIELD_TRIP_KAKAO_RESPONSE_TYPE}`;
  const kakaoSignLink = `https://kauth.kakao.com/oauth/authorize?client_id=${process.env.FIELD_TRIP_KAKAO_CLIENT_ID}&redirect_uri=${process.env.FIELD_TRIP_KAKAO_REDIRECT_URI_SIGNUP}&response_type=${process.env.FIELD_TRIP_KAKAO_RESPONSE_TYPE}`;

  const requestLogin = async (code) => {
    try {
      const res = await API.POST({
        url: '/api/login/oauth',
        data: { code, providerName: 'KAKAO' },
      });
      setCookie(null, 'jwt', res.data.accessToken, {
        path: '/',
        maxAge: 30000000,
      });
      router.push('/');
    } catch (err) {
      router.push(kakaoSignLink);
    }
  };

  useEffect(() => {
    const { code } = router.query;
    if (!!code) requestLogin(code);
  });

  return (
    <StyledWrapper>
      <div className="home-banner">
        <img src="/icons/mainIcon.png" className="banner-icon" />
        <div className="banner-text">fieldtrip</div>
      </div>
      <div className="login-box">
        <img src="/icons/naverLoginIcon.png" />
        <Link href={kakaoLink}>
          <img src="/icons/kakaoLoginIcon.png" />
        </Link>
      </div>
    </StyledWrapper>
  );
};

export default ScreenLogin;

const StyledWrapper = styled.div`
  width: 100vw;
  height: 100vh;
  background: var(--color-main);
  color: var(--color-white);
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  .home-banner {
    text-align: center;
    margin-bottom: 50px;

    .banner-icon {
      width: 55px;
      height: 55px;
      margin-bottom: 10px;
    }

    .banner-text {
      font-size: 22px;
      line-height: 28px;
    }
  }

  .login-box {
    display: flex;
  }
`;
