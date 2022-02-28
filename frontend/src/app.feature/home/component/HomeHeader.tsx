import React from 'react';
import styled from 'styled-components';

const HomeHeader = () => {
  return (
    <StyledWrapper>
      <div className="logo">
        <img src="/images/common/logo-text.png" alt="로고 텍스트" />
      </div>
      <div className="header-button">
        <img src="/images/common/icon-alarm.png" alt="알림" />
        <img src="/images/common/icon-message.png" alt="메신저" />
      </div>
    </StyledWrapper>
  );
};

export default HomeHeader;

const StyledWrapper = styled.div`
  height: 44px;
  padding: 0 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  position: sticky;
  top: 0;
  z-index: 10;
  background-color: var(--color-white);

  .logo {
    img {
      width: 81px;
      height: 29px;
    }
  }

  .header-button {
    img {
      &:first-child {
        margin-right: 18.3px;
      }
      padding: 16px 0 8px;
      width: 20px;
    }
  }
`;
