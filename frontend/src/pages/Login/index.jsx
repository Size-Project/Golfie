import React, { useEffect } from 'react';
import { Wrapper } from './styled';
import { useLocation, useNavigate, createSearchParams } from 'react-router-dom';
import mainIcon from 'assets/icons/mainIcon.png';
import googleLoginIcon from 'assets/icons/googleLoginIcon.png';
import naverLoginIcon from 'assets/icons/naverLoginIcon.png';
import kakaoLoginIcon from 'assets/icons/kakaoLoginIcon.png';
import axios from 'axios';
import qs from 'qs';

axios.defaults.withCredentials = true;

const LoginPage = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const params = new URLSearchParams(location.search);
  const kakao_client_id = '0e7846ce2b8ea8f27a487ba067a3d527';
  const naver_client_id = 'QB8NNIWvrYAYTP5Wffcv';
  const naver_state = 'hLiDdL2uhPtsftcU';
  const kakao_redirect_uri = 'http://localhost:3000/account/login';
  const naver_redirect_uri = 'http://localhost:3000/account/login/redirect';
  const response_type = 'code';
  const kakaoLink = `https://kauth.kakao.com/oauth/authorize?client_id=${kakao_client_id}&redirect_uri=${kakao_redirect_uri}&response_type=${response_type}
  `;
  const naverLink = `https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=${naver_client_id}&redirect_uri=${naver_redirect_uri}&state=${naver_state}`;

  useEffect(() => {
    const code = params.get('code');
    if (code !== null) {
      async function getUserdata() {
        const response = await axios.post('/api/signup/oauth/prepare', {
          code,
          providerName: 'KAKAO',
        });
        if (response.data) {
          navigate({
            pathname: '/account/signup',
            search: `?${createSearchParams(response.data)}`,
          });
        }
      }
      getUserdata();
    }
  }, []);

  const handleClick = async () => {};
  return (
    <Wrapper>
      <header className="home">
        <img src={mainIcon} alt="홈 아이콘" className="home-icon" />
        <div>fieldtrip</div>
      </header>
      <section className="login-box">
        <div className="icons">
          <a href={naverLink}>
            <img
              src={naverLoginIcon}
              onClick={handleClick}
              alt="네이버 로그인 아이콘"
              className="login-icon"
            />
          </a>
          <a href={kakaoLink}>
            <img
              src={kakaoLoginIcon}
              onClick={handleClick}
              alt="카카오 로그인 아이콘"
              className="login-icon"
            />
          </a>
          <img
            src={googleLoginIcon}
            onClick={handleClick}
            alt="구글 로그인 아이콘"
            className="login-icon"
          />
        </div>
        <div className="home-button">로그인하지 않고 홈으로 이동하기</div>
      </section>
    </Wrapper>
  );
};

export default LoginPage;
