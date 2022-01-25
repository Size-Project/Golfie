import React, { useEffect } from 'react';
import { Wrapper } from './styled';
import { useParams, useLocation } from 'react-router-dom';
import mainIcon from 'assets/icons/mainIcon.png';
import googleLoginIcon from 'assets/icons/googleLoginIcon.png';
import naverLoginIcon from 'assets/icons/naverLoginIcon.png';
import kakaoLoginIcon from 'assets/icons/kakaoLoginIcon.png';
import axios from 'axios';
// REST API KEY = 0e7846ce2b8ea8f27a487ba067a3d527
// GET /oauth/authorize?client_id={REST_API_KEY}&redirect_uri={REDIRECT_URI}&response_type=code HTTP/1.1
// Host: kauth.kakao.com
axios.defaults.withCredentials = true;

const LoginPage = () => {
  const location = useLocation();
  const params = new URLSearchParams(location.search);
  const client_id = '0e7846ce2b8ea8f27a487ba067a3d527';
  const redirect_uri = 'http://localhost:3000/account/login';
  const response_type = 'code';
  const kakaoLink = `https://kauth.kakao.com/oauth/authorize?client_id=${client_id}&redirect_uri=${redirect_uri}&response_type=${response_type}
  `;

  useEffect(() => {
    const code = params.get('code');
    if (code !== null) {
      console.log(code);
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
