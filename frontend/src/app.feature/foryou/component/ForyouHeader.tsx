import React from 'react';
import styled from 'styled-components';

const ForyouHeader = () => {
  return (
    <StyledWrapper className="foryou-header-wrap">
      <div className="logo">For You</div>
      <div className="header-button">
        <img src="/images/common/icon-alarm.png" alt="알림" />
        <img src="/images/common/icon-message.png" alt="메신저" />
      </div>
    </StyledWrapper>
  );
};

export default ForyouHeader;

const StyledWrapper = styled.div`
  height: 44px;
  padding: 0 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  position: sticky;
  top: 0;
  z-index: 10;
  background-color: var(--color-gray-100);

  .logo {
    font-family: 'Co Headline';
    font-size: 22px;
    line-height: 29px;
  }

  .header-button {
    img {
      margin-right: 18.3px;

      &:last-child {
        margin-right: 0;
      }
      padding: 16px 0 8px;
      width: 20px;
    }
  }
`;
