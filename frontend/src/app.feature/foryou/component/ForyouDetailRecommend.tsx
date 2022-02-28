import React from 'react';
import styled from 'styled-components';

const ForyouDetailRecommend = ({ userInfo }) => {
  return (
    <StyledWrapper>
      <div className="place-title">
        다가오는 라운딩을 위한
        <br />
        <b>#HEALING 플레이스</b>
      </div>
    </StyledWrapper>
  );
};

export default ForyouDetailRecommend;

const StyledWrapper = styled.div`
  padding: 0 20px;
  margin-bottom: 50px;

  .place-title {
    font-size: 20px;
    font-weight: 500;
    line-height: 26px;
    margin-bottom: 20px;

    b {
      color: var(--color-main);
    }
  }
`;
