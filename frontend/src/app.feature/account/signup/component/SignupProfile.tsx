import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import SignupInput from 'app.feature/account/signup/component/SignupInput';
import API from 'app.modules/api';

const SignupProfile = ({ handleChangeStage }) => {
  const [activeNext, setActiveNext] = useState(false);
  const [error, setError] = useState(null);
  const [profileInfo, setProfileInfo] = useState({
    nickname: '',
    job: '',
    averageHit: '',
  });

  const requestValidate = async (nickname) => {
    try {
      const res = await API.POST({
        url: '/api/validate/nickname',
        data: { nickname },
      });
      if (res.status !== 200) throw res;
      setError(null);
    } catch (err) {
      setError(err.response.data.message);
    }
  };

  useEffect(() => {
    setActiveNext(
      !!profileInfo.nickname && !!profileInfo.job && !!profileInfo.averageHit
    );
  }, [profileInfo]);

  return (
    <StyledWrapper>
      <div className="sign-up-header">프로필 정보 입력</div>
      <div className="profile-input-box">
        <SignupInput
          name="nickname"
          label="닉네임"
          onChange={(e) => {
            requestValidate(e.target.value);
            setProfileInfo((prev) => ({
              ...prev,
              nickname: e.target.value,
            }));
          }}
          error={error}
        />
        <SignupInput
          name="job"
          label="직업"
          onChange={(e) => {
            setProfileInfo((prev) => ({
              ...prev,
              job: e.target.value,
            }));
          }}
        />
        <SignupInput
          name="averageHit"
          label="평균타수"
          type="number"
          onChange={(e) => {
            setProfileInfo((prev) => ({
              ...prev,
              averageHit: e.target.value,
            }));
          }}
        />
      </div>
      <div className="stage-button-wrap">
        <div
          className={`stage-button ${String(activeNext)}`}
          onClick={() => (activeNext ? handleChangeStage('keyword') : null)}
        >
          다음
        </div>
      </div>
    </StyledWrapper>
  );
};

export default SignupProfile;

const StyledWrapper = styled.div`
  .sign-up-header {
    height: 44px;
    background: var(--color-main);
    color: var(--color-white);
    display: flex;
    align-items: center;
    justify-content: center;
    margin-bottom: 60px;
  }

  .profile-input-box {
    padding: 0 20px;
  }

  .stage-button-wrap {
    position: fixed;
    width: 100%;
    bottom: 40px;
  }

  .stage-button {
    background: var(--color-main);
    color: var(--color-white);
    height: 40px;
    width: 275px;
    display: flex;
    align-items: center;
    justify-content: center;
    margin: 0 auto;
    border-radius: 20px;

    &.false {
      background: var(--color-gray-200);
    }
  }
`;
