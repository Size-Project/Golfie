import React, { useState, useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import axios from 'axios';
import { setCookie } from 'utils/cookie';
import API from 'api';
import kakaoLoginIcon from 'assets/icons/kakaoLoginIcon.png';
import checkIcon from 'assets/icons/checkIcon.png';
import { Wrapper } from './styled';
import SignUpHeader from 'layout/SignUpHeader';

const SignupPage = (a) => {
  const [nickname, setNickname] = useState('');
  const [nickCheck, setNickCheck] = useState(true);
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
    if (response.status === 200) setNickCheck(false);
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
      <form action="" className="signup-form">
        <section className="input-text">
          <div className="input-header">
            <img src={checkIcon} alt="" />
            <div className="input-title">닉네임</div>
          </div>
          <input
            type="text"
            value={nickname}
            className="text"
            placeholder="입력해주세요"
            onChange={nicknameConfirm}
          />
          {nickCheck && (
            <div className="error-message">닉네임이 잘못되었습니다</div>
          )}
        </section>
        <section className="input-text">
          <div className="input-header">
            <img src={checkIcon} alt="" />
            <div className="input-title">직업</div>
          </div>
          <input
            type="text"
            value={nickname}
            className="text"
            placeholder="입력해주세요"
            onChange={nicknameConfirm}
          />
        </section>
        <section className="input-text">
          <div className="input-header">
            <img src={checkIcon} alt="" />
            <div className="input-title">타수</div>
          </div>
          <input
            type="text"
            value={nickname}
            className="text"
            placeholder="입력해주세요"
            onChange={nicknameConfirm}
          />
        </section>

        <section className="input-select">
          <div className="input-select-title">함께하고 싶은 평균 타수</div>
          <div className="select-item-box">
            <div className="select-item">
              <div className="item-text">126타 이상</div>
            </div>
            <div className="select-item">
              <div className="item-text">100타 이상</div>
            </div>
            <div className="select-item">
              <div className="item-text">90타 전후</div>
            </div>
            <div className="select-item">
              <div className="item-text">72 - 81타</div>
            </div>
          </div>
        </section>
        <section className="input-select">
          <div className="input-select-title">함께하고 싶은 연령대</div>
          <div className="select-item-box">
            <div className="select-item">
              <div className="item-text">20세 미만</div>
            </div>
            <div className="select-item">
              <div className="item-text">21 - 25세</div>
            </div>
            <div className="select-item">
              <div className="item-text">26 - 30세</div>
            </div>
            <div className="select-item">
              <div className="item-text">31 - 35세</div>
            </div>
            <div className="select-item">
              <div className="item-text">36 - 40세</div>
            </div>
            <div className="select-item">
              <div className="item-text">40세 이상</div>
            </div>
          </div>
        </section>
        <section className="input-select">
          <div className="input-select-title">좋아하는 분위기</div>
          <div className="select-item-box">
            <div className="select-item">
              <div className="item-text">편안한</div>
            </div>
            <div className="select-item">
              <div className="item-text">즐거운</div>
            </div>
            <div className="select-item">
              <div className="item-text">열정적인</div>
            </div>
            <div className="select-item">
              <div className="item-text">교훈적인</div>
            </div>
            <div className="select-item">
              <div className="item-text">가벼운</div>
            </div>
            <div className="select-item">
              <div className="item-text">진지한</div>
            </div>
            <div className="select-item">
              <div className="item-text">칭찬하는</div>
            </div>
            <div className="select-item">
              <div className="item-text">침착한</div>
            </div>
            <div className="select-item">
              <div className="item-text">예의바른</div>
            </div>
            <div className="select-item">
              <div className="item-text">긍정적인</div>
            </div>
            <div className="select-item">
              <div className="item-text">조용한</div>
            </div>
            <div className="select-item">
              <div className="item-text">존중하는</div>
            </div>
          </div>
        </section>

        <button type="submit" disabled={nickCheck} onClick={handleClick}>
          Join
        </button>
      </form>
    </Wrapper>
  );
};

export default SignupPage;
