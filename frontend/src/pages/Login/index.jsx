import React, { useEffect } from 'react';
import { Wrapper } from './styled';
import { useLocation, useNavigate } from 'react-router-dom';
import mainIcon from 'assets/icons/mainIcon.png';
import googleLoginIcon from 'assets/icons/googleLoginIcon.png';
import naverLoginIcon from 'assets/icons/naverLoginIcon.png';
import kakaoLoginIcon from 'assets/icons/kakaoLoginIcon.png';
import axios from 'axios';
import API from 'api';

axios.defaults.withCredentials = true;

const LoginPage = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const params = new URLSearchParams(location.search);
  const kakaoLink = `https://kauth.kakao.com/oauth/authorize?client_id=${process.env.REACT_APP_KAKAO_CLIENT_ID}&redirect_uri=${process.env.REACT_APP_KAKAO_REDIRECT_URI}&response_type=${process.env.REACT_APP_KAKAO_RESPONSE_TYPE}`;
  useEffect(() => {
    const code = params.get('code');
    if (code !== null) {
      async function login() {
        try {
          const response = await API.POST({
            url: '/api/login/oauth',
            data: { code, providerName: 'KAKAO' },
          });
        } catch (error) {
          navigate(`/account/signup/?code=${code}`);
        }
      }
      login();
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
          <img
            src={naverLoginIcon}
            onClick={handleClick}
            alt="네이버 로그인 아이콘"
            className="login-icon"
          />
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
