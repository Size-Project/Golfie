import React from 'react';
import styled from 'styled-components';
import JoinCardHorizontal from 'app.components/JoinCard/JoinCardHorizontal';

const JoinRecommend = () => {
  return (
    <StyledWrapper>
      <div className="top-title">
        설레는 라운딩을 위한
        <br />
        <b>조인 추천</b>
      </div>
      <div className="recommend-slide-wrap">
        {[1, 2, 3, 4, 5, 6].map((item, idx) => (
          <JoinCardHorizontal key={idx} />
        ))}
      </div>
    </StyledWrapper>
  );
};

export default JoinRecommend;

export const StyledWrapper = styled.div`
  padding: 10px 0;

  .top-title {
    padding: 0 20px;
    line-height: 26px;
    font-size: 20px;
    margin-bottom: 10px;

    b {
      color: var(--color-main);
      font-weight: bold;
    }
  }

  .recommend-slide-wrap {
    padding: 10px 0;
    display: flex;
    overflow-y: visible;
    overflow-x: scroll;
    width: 100%;
    gap: 20px;
    height: 100%;
  }
`;
