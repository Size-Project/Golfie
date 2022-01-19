import React from 'react';
import { Wrapper } from './styled';
import mainIcon from 'assets/icons/mainIcon.png';
import googleLoginIcon from 'assets/icons/googleLoginIcon.png';
import naverLoginIcon from 'assets/icons/naverLoginIcon.png';
import kakaoLoginIcon from 'assets/icons/kakaoLoginIcon.png';

const LoginPage = () => {
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
            alt="네이버 로그인 아이콘"
            className="login-icon"
          />
          <img
            src={kakaoLoginIcon}
            alt="카카오 로그인 아이콘"
            className="login-icon"
          />
          <img
            src={googleLoginIcon}
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
