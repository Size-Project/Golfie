import React, { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import qs from 'qs';
import axios from 'axios';
import { setCookie } from 'utils/cookie';

const SignupPage = (a) => {
  const [nickname, setNickname] = useState('');
  const [buttonOff, setButtonOff] = useState(true);
  const location = useLocation();
  const navigate = useNavigate();
  const queryData = qs.parse(location.search, { ignoreQueryPrefix: true });

  const refreshPage = () => {
    window.location.reload();
  };

  const handleChange = async (e) => {
    const { name, value } = e.target;
    setNickname(value);
    const response = await axios.post('/api/validate/nickname', {
      nickname: value,
    });
    if (response.status === 200) setButtonOff(false);
  };

  const handleClick = async (e) => {
    e.preventDefault();
    const signupDate = { ...queryData, nickname, bio: 'hello' };
    const response = await axios.post('/api/signup/oauth', signupDate);
    setCookie('jwt', response.data.accessToken, { path: '/' });
    // console.log(response.data.accessToken);
    navigate('/');
    refreshPage();
  };

  return (
    <div>
      <form action="">
        <input
          type="text"
          value={nickname}
          className="text"
          placeholder="닉네임"
          onChange={handleChange}
        />
        <button type="submit" disabled={buttonOff} onClick={handleClick}>
          회원가입
        </button>
      </form>
    </div>
  );
};

export default SignupPage;
