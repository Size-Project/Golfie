import React from 'react';
import styled from 'styled-components';
import SVGLeft from '../../../../../public/images/svg/arrow-left.svg';

const JoinCreateHeader = () => {
  return (
    <StyledWrapper>
      <SVGLeft />
      <div className="title">조인 만들기</div>
      <div className="right-icon" />
    </StyledWrapper>
  );
};

export default JoinCreateHeader;

const StyledWrapper = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 44px;
  width: 100%;
  background: var(--color-white);
  padding: 7px;
  z-index: 2;
  position: sticky;

  .right-icon {
    width: 30px;
  }

  .title {
    font-size: 16px;
    font-weight: 500;
  }

  svg {
    width: 30px;
    height: 30px;

    path {
      fill: var(--color-gray-300);
    }
  }
`;
