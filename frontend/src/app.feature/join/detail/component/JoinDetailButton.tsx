import React from 'react';
import styled from 'styled-components';
import API from 'app.modules/api';
import { API_JOIN_ROUNDINGS } from 'app.modules/api/fieldtrip.join';
import { useRouter } from 'next/router';

const JoinDetailButton = ({ joinDetailData, joinId, login, userInfo }) => {
  const router = useRouter();

  const handleJoinRounding = async () => {
    try {
      await API.PUT({ url: API_JOIN_ROUNDINGS(joinId) });
      location.reload();
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <StyledWrapper>
      {!login ? (
        <div
          className="join-button false"
          onClick={() => router.push('/account/login')}
        >
          로그인하기
        </div>
      ) : (
        <div
          className={`join-button ${String(
            !!joinDetailData.attendees.find((item) => userInfo.id === item.id)
          )}`}
          onClick={
            joinDetailData.attendees.find((item) => userInfo.id === item.id)
              ? null
              : handleJoinRounding
          }
        >
          조인하기
        </div>
      )}
      <div className="join-like">
        <img src="/images/Join/1762@3x.png" />
      </div>
    </StyledWrapper>
  );
};

const StyledWrapper = styled.div`
  display: flex;
  position: fixed;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 120px;
  padding: 19px 20px 61px 20px;
  box-shadow: 0 -1px 10px 0 rgba(0, 0, 0, 0.1);
  background-color: var(--color-white);

  .join-button {
    margin-right: 10px;
    width: 100%;
    background-color: var(--color-sub);
    height: 40px;
    padding: 10px 0;
    text-align: center;
    border-radius: 20px;
    color: var(--color-white);

    &.true {
      background-color: var(--color-gray-100);
    }
  }

  .join-like {
    max-height: 40px;
    max-width: 40px;
    min-height: 40px;
    min-width: 40px;
  }
`;

export default JoinDetailButton;
