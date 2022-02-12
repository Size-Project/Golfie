import React, { useState, useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import axios from 'axios';
import { setCookie } from 'utils/cookie';
import API from 'api';
import kakaoLoginIcon from 'assets/icons/kakaoLoginIcon.png';
import { Wrapper } from './styled';
import SignUpHeader from 'layout/SignUpHeader';

const SignupPage = (a) => {
  const [nickname, setNickname] = useState('');
  const [buttonOff, setButtonOff] = useState(true);
  const [user, setUser] = useState({});
  const location = useLocation();
  const navigate = useNavigate();
  const kakaoLink = `https://kauth.kakao.com/oauth/authorize?client_id=${process.env.REACT_APP_KAKAO_CLIENT_ID}&redirect_uri=${process.env.REACT_APP_KAKAO_REDIRECT_URI_SIGNUP}&response_type=${process.env.REACT_APP_KAKAO_RESPONSE_TYPE}`;
  const params = new URLSearchParams(location.search);

  const nicknameConfirm = async (e) => {
    const { value } = e.target;
    setNickname(value);
    const response = await axios.post('/api/validate/nickname', {
      nickname: value,
    });
    if (response.status === 200) setButtonOff(false);
  };

  const handleClick = async (e) => {
    e.preventDefault();
    const signupDate = { nickname, bio: 'hello', ...user };
    const response = await axios.post('/api/signup/oauth', signupDate);
    setCookie('jwt', response.data.accessToken, { path: '/' });
    navigate('/');
    window.location.reload();
  };

  useEffect(() => {
    const code = params.get('code');
    async function readySignup() {
      try {
        const response = await API.POST({
          url: '/api/signup/oauth/prepare',
          data: { code, providerName: 'KAKAO' },
        });
        setUser({ ...response.data });
      } catch (error) {
        alert('카카오 회원가입 에러');
      }
    }
    if (code !== null) readySignup();
  });

  return (
    <Wrapper>
      <SignUpHeader />
      <a href={kakaoLink}>
        <img
          src={kakaoLoginIcon}
          alt="카카오 로그인 아이콘"
          className="login-icon"
        />
      </a>
      <form action="">
        <input
          type="text"
          value={nickname}
          className="text"
          placeholder="닉네임"
          onChange={nicknameConfirm}
        />
        <button type="submit" disabled={buttonOff} onClick={handleClick}>
          Join
        </button>
      </form>
    </Wrapper>
  );
};

export default SignupPage;
