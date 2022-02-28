import React from 'react';
import styled from 'styled-components';

const MySettings = () => {
  return (
    <StyledWrapper>
      <div className="settings-item">
        <img src="/images/My/wish-list.png" />
        <div className="text">찜 목록</div>
      </div>
      <div className="settings-item">
        <img src="/images/My/join-list.png" />
        <div className="text">조인 기록</div>
      </div>
      <div className="settings-item">
        <img src="/images/My/post-list.png" />
        <div className="text">내가 남긴 글</div>
      </div>
      <div className="settings-item">
        <img src="/images/My/reserve-list.png" />
        <div className="text">예약 목록</div>
      </div>
    </StyledWrapper>
  );
};

export default MySettings;

const StyledWrapper = styled.div`
  padding: 0 20px;
  display: flex;

  .settings-item {
    width: 25%;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;

    img {
      width: 40px;
      margin-bottom: 6px;
    }

    .text {
      font-size: 10px;
      color: var(--color-gray-300);
    }
  }
`;
